package com.sursun.houck.lsg;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.radar.RadarNearbyInfo;
import com.baidu.mapapi.radar.RadarNearbyResult;
import com.baidu.mapapi.radar.RadarNearbySearchOption;
import com.baidu.mapapi.radar.RadarSearchError;
import com.baidu.mapapi.radar.RadarSearchListener;
import com.baidu.mapapi.radar.RadarSearchManager;
import com.baidu.mapapi.radar.RadarUploadInfo;
import com.baidu.mapapi.radar.RadarUploadInfoCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MapActivity extends Activity implements RadarUploadInfoCallback,RadarSearchListener,BDLocationListener,BaiduMap.OnMarkerClickListener, BaiduMap.OnMapClickListener{
    //
    //界面空间相关
    private CustomViewPager mPager;//自定义viewPager，目的是禁用手势滑动
    private List<View> listViews;
    private String userId;
    private String userDes;
    private Button switchBtn;
    private Button searchNearbyBtn;
    private Button clearRstBtn;

    private int index = 0;
    private Button listPreBtn;
    private Button listNextBtn;
    private TextView listCurPage;
    private Button mapPreBtn;
    private Button mapNextBtn;
    private TextView mapCurPage;

    /* 定位相关 */
    private LocationClient mLocClient;
    private int pageIndex = 0;
    private int curPage = 0;
    private int totalPage = 0;
    private LatLng pt = null;
    private boolean isFirstLoc = true;

    //地图相关
    private MapView mMapView = null;
    private BaiduMap mBaiduMap = null;
    private TextView popupText = null;//泡泡view
    private BitmapDescriptor ff3 = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);

    //周边雷达相关
    private RadarSearchManager mRadarManager = null;
    private RadarNearbyResult listResult = null;
    private ListView mResultListView = null;
    private RadarResultListAdapter mResultListAdapter = null;
    private String userID = "";
    private String userComment = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // SDKInitializer.initialize(this);
        setContentView(R.layout.activity_map);

        //初始化UI和地图
        initUI();

        //周边雷达设置监听
        mRadarManager = RadarSearchManager.getInstance();
        mRadarManager.addNearbyInfoListener(this);

        //周边雷达设置用户，id为空默认是设备标识
        mRadarManager.setUserID(userID);
        mRadarManager.startUploadAuto(this, 5000);

        // 定位初始化
        mLocClient = new LocationClient(getApplicationContext());
        mLocClient.registerLocationListener(this);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();
    }

    private void initUI() {

        Log.w("MapActivity","initUI------------------W");

        mPager = (CustomViewPager) findViewById(R.id.viewpager);
        listViews = new ArrayList<View>();
        LayoutInflater mInflater = getLayoutInflater();

        View layout = mInflater.inflate(R.layout.activity_radarlist, null);
        View mapLayout = mInflater.inflate(R.layout.activity_radarmap, null);
        //地图初始化
        mMapView = (MapView) mapLayout.findViewById(R.id.map);
        mBaiduMap = mMapView.getMap();

        mBaiduMap.setOnMarkerClickListener(this);
        mBaiduMap.setOnMapClickListener(this);
        mBaiduMap.setMyLocationEnabled(true);

        listViews.add(mapLayout);
        listViews.add(layout);

        mPager.setAdapter(new MyPagerAdapter(listViews));
        mPager.setCurrentItem(0);
        mPager.setOnPageChangeListener(new MyOnPageChangeListener());

        Random r=new Random();
        int n = r.nextInt(100)+1;
        userId = "houck"+n;
        userDes= "描述" + n;

        switchBtn = (Button)findViewById(R.id.switchButton);
        searchNearbyBtn = (Button)findViewById(R.id.searchNearByButton);
        clearRstBtn = (Button)findViewById(R.id.clearResultButton);
        listPreBtn = (Button)layout.findViewById(R.id.radarlistpre);
        listNextBtn = (Button)layout.findViewById(R.id.radarlistnext);
        listCurPage = (TextView)layout.findViewById(R.id.radarListPage);
        mapPreBtn = (Button)mapLayout.findViewById(R.id.radarmappre);
        mapNextBtn = (Button)mapLayout.findViewById(R.id.radarmapnext);
        mapCurPage = (TextView)mapLayout.findViewById(R.id.radarMapPage);
        clearRstBtn.setEnabled(false);

        listPreBtn.setVisibility(View.INVISIBLE);
        listNextBtn.setVisibility(View.INVISIBLE);
        mapPreBtn.setVisibility(View.INVISIBLE);
        mapNextBtn.setVisibility(View.INVISIBLE);
        listCurPage.setVisibility(View.INVISIBLE);
        mapCurPage.setVisibility(View.INVISIBLE);

        ArrayList<RadarNearbyInfo> infos = new ArrayList<RadarNearbyInfo>();
        mResultListAdapter = new RadarResultListAdapter(null);
        mResultListView = (ListView) layout.findViewById(R.id.radar_list);
        mResultListView.setAdapter(mResultListAdapter);
        mResultListAdapter.notifyDataSetChanged();
    }

    /**
     * 查找周边的人
     * @param v
     */
    public void searchNearby(View v) {
        searchNearby();
    }

    private void searchNearby() {
        if (pt == null) {
            Toast.makeText(MapActivity.this, "未获取到位置", Toast.LENGTH_LONG)
                    .show();
            return;
        }
        pageIndex = 0;
        searchRequest(pageIndex);
    }

    private void searchRequest(int index) {

        Log.w("MapActivity","searchRequest----" + index);
        curPage = 0;
        totalPage = 0;

        RadarNearbySearchOption option = new RadarNearbySearchOption().centerPt(pt).pageNum(pageIndex).radius(2000);
        mRadarManager.nearbyInfoRequest(option);

        listPreBtn.setVisibility(View.INVISIBLE);
        listNextBtn.setVisibility(View.INVISIBLE);
        mapPreBtn.setVisibility(View.INVISIBLE);
        mapNextBtn.setVisibility(View.INVISIBLE);
        listCurPage.setText("0/0");
        mapCurPage.setText("0/0");
        mBaiduMap.hideInfoWindow();
    }

    //viewPager切换
    public void switchClick(View v) {
        if (index == 0) {
            //切换为列表
            index = 1;
            switchBtn.setText("地图");
        } else {
            //切换为地图
            index = 0;
            switchBtn.setText("列表");
        }
        mPager.setCurrentItem(index);

    }

    /**
     * 更新结果列表
     * @param res
     */
    public void parseResultToList(RadarNearbyResult res) {
        if (res == null) {
            if (mResultListAdapter.list != null) {
                mResultListAdapter.list.clear();
                mResultListAdapter.notifyDataSetChanged();
            }

        } else {
            mResultListAdapter.list = res.infoList;
            mResultListAdapter.notifyDataSetChanged();
            if (curPage > 0) {
                listPreBtn.setVisibility(View.VISIBLE);
            }
            if (totalPage - 1 > curPage) {
                listNextBtn.setVisibility(View.VISIBLE);
            }
            if (totalPage > 0) {
                listCurPage.setVisibility(View.VISIBLE);
                listCurPage.setText(String.valueOf(curPage + 1)+"/"+String.valueOf(totalPage));
            }
        }
    }

    /**
     * 更新结果地图
     * @param res
     */
    public void parseResultToMap(RadarNearbyResult res) {
        mBaiduMap.clear();
        if (res != null && res.infoList != null && res.infoList.size() > 0) {
            for (int i = 0;i< res.infoList.size();i++) {
                MarkerOptions option = new MarkerOptions().icon(ff3).position(res.infoList.get(i).pt);
                Bundle des = new Bundle();
                if (res.infoList.get(i).comments == null || res.infoList.get(i).comments.equals("")) {
                    des.putString("des", "没有备注");
                } else {
                    des.putString("des", res.infoList.get(i).comments);
                }

                option.extraInfo(des);
                mBaiduMap.addOverlay(option);
            }
        }
        if (curPage > 0) {
            mapPreBtn.setVisibility(View.VISIBLE);
        }
        if (totalPage - 1 > curPage) {
            mapNextBtn.setVisibility(View.VISIBLE);
        }
        if (totalPage > 0) {
            mapCurPage.setVisibility(View.VISIBLE);
            mapCurPage.setText(String.valueOf(curPage + 1) + "/" + String.valueOf(totalPage));
        }

    }

    /**
     * 实现上传callback，自动上传
     */
    @Override
    public RadarUploadInfo OnUploadInfoCallback() {

        RadarUploadInfo info = new RadarUploadInfo();
        info.comments = userComment;
        info.pt = pt;
        Log.e("MapActivity", "OnUploadInfoCallback");
        return info;
    }

    @Override
    public void onGetNearbyInfoList(RadarNearbyResult result, RadarSearchError error) {

        Log.w("MapActivity", "onGetNearbyInfoList");

        if (error == RadarSearchError.RADAR_NO_ERROR) {

            Log.w("MapActivity", "search nearby success");

            //获取成功
            listResult = result;
            curPage = result.pageIndex;
            totalPage = result.pageNum;
            //处理数据
            parseResultToList(listResult);
            parseResultToMap(listResult);
            clearRstBtn.setEnabled(true);
        } else {
            //获取失败
            curPage = 0;
            totalPage = 0;
            Log.w("MapActivity", "search nearby failed");
        }

    }

    @Override
    public void onGetUploadState(RadarSearchError error) {

        Log.w("MapActivity", "onGetUploadState");

        if (error == RadarSearchError.RADAR_NO_ERROR) {
            //上传成功
            Log.w("MapActivity", "Upload Success");
        } else {
            //上传失败
            Log.w("MapActivity", "Upload Failed");
        }
    }

    @Override
    public void onGetClearInfoState(RadarSearchError error) {
        // TODO Auto-generated method stub
        if (error == RadarSearchError.RADAR_NO_ERROR) {
            //清除成功
            Toast.makeText(MapActivity.this, "清除位置成功", Toast.LENGTH_LONG)
                    .show();
        } else {
            //清除失败
            Toast.makeText(MapActivity.this, "清除位置失败", Toast.LENGTH_LONG)
                    .show();
        }
    }

    /**
     * 定位SDK监听函数
     */
    @Override
    public void onReceiveLocation(BDLocation location) {
        // map view 销毁后不在处理新接收的位置
        Log.w("MapActivity","onReceiveLocation");
        if (location == null || mMapView == null || mBaiduMap == null) {

            Log.w("MapActivity", "location == null || mMapView == null || mBaiduMap == null");
            return;
        }

        pt = new LatLng(location.getLatitude(), location.getLongitude());

        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(location.getRadius())
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(100).latitude(location.getLatitude())
                .longitude(location.getLongitude()).build();

        if (mBaiduMap != null) {
            mBaiduMap.setMyLocationData(locData);
            Log.w("MapActivity", "mBaiduMap.setMyLocationData");

            if(isFirstLoc == true){

                Log.w("MapActivity", "First Location update map");

                isFirstLoc = false;
                MapStatus mapStatus = new MapStatus.Builder()
                        .target(pt)
                        .zoom(18)
                        .build();

                //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
                MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
                //改变地图状态
                mBaiduMap.setMapStatus(mapStatusUpdate);

                //searchNearby();
            }


        } else {
            Log.w("MapActivity", "mBaiduMap == null");
        }

    }

    @Override
    public void onMapClick(LatLng point) {
        // TODO Auto-generated method stub
        mBaiduMap.hideInfoWindow();
    }

    @Override
    public boolean onMapPoiClick(MapPoi poi) {
        // TODO Auto-generated method stub

        return false;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        // TODO Auto-generated method stub
        mBaiduMap.hideInfoWindow();
        if (marker != null) {
            popupText = new TextView(MapActivity.this);
            popupText.setBackgroundResource(R.drawable.popup);
            popupText.setTextColor(0xFF000000);
            popupText.setText(marker.getExtraInfo().getString("des"));
            mBaiduMap.showInfoWindow(new InfoWindow(popupText, marker.getPosition(), -47));
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(marker.getPosition());
            mBaiduMap.setMapStatus(update);
            return true;
        } else {
            return false;
        }
    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int arg0) {
            if (arg0 == 0) {
                //切换为列表
                index = 0;
                switchBtn.setText("地图");
            } else {
                //切换为地图
                index = 1;
                switchBtn.setText("列表");
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }

    /**
     * 结果列表listview适配器
     *
     */
    private class RadarResultListAdapter extends BaseAdapter {
        public List<RadarNearbyInfo> list;
        public RadarResultListAdapter(List<RadarNearbyInfo> res) {
            super();
            this.list = res;
        }

        @Override
        public View getView(int index, View convertView, ViewGroup parent) {
            convertView = View.inflate(MapActivity.this,
                    R.layout.point_info_item, null);
            TextView title = (TextView) convertView.findViewById(R.id.title);
            TextView desc = (TextView) convertView.findViewById(R.id.desc);
            title.setTextColor(Color.parseColor("#0000FF"));
            desc.setTextColor(Color.parseColor("#0000FF"));
            if (list == null || list.size() == 0 || index >= list.size()) {
                desc.setText("");
                title.setText("");
            } else {
                if (list.get(index).comments == null || list.get(index).comments.equals("")) {
                    desc.setText(String.valueOf(list.get(index).distance) + "米"+ "_没有备注");
                } else {
                    desc.setText(String.valueOf(list.get(index).distance) + "米"+ "_"+list.get(index).comments);
                }

                title.setText(list.get(index).userID);
            }


            return convertView;
        }

        @Override
        public int getCount() {
            if (list == null || (list!= null && list.size() < 10)) {
                return 10;
            } else {
                return list.size();
            }

        }

        @Override
        public Object getItem(int index) {
            if (list == null) {
                return new RadarNearbyInfo();
            } else {
                return list.get(index);
            }

        }

        @Override
        public long getItemId(int id) {
            return id;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        // 退出时销毁定位
        mLocClient.stop();

        //释放周边雷达相关
        mRadarManager.removeNearbyInfoListener(this);
        mRadarManager.clearUserInfo();
        mRadarManager.destroy();

        //释放地图
        ff3.recycle();
        mMapView.onDestroy();
        mBaiduMap = null;

        super.onDestroy();
    }
    @Override
    protected void onResume() {

        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
       // mMapView.onResume();

        super.onResume();
    }
    @Override
    protected void onPause() {

        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
       // mMapView.onPause();

        super.onPause();
    }
}
