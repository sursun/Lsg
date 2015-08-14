package com.sursun.houck.lsg;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.radar.RadarNearbyInfo;
import com.baidu.mapapi.radar.RadarNearbyResult;
import com.baidu.mapapi.radar.RadarNearbySearchOption;
import com.baidu.mapapi.radar.RadarSearchError;
import com.baidu.mapapi.radar.RadarSearchListener;
import com.baidu.mapapi.radar.RadarSearchManager;
import com.baidu.mapapi.radar.RadarUploadInfo;
import com.baidu.mapapi.radar.RadarUploadInfoCallback;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.utils.DistanceUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class MapActivity extends Activity implements RadarUploadInfoCallback,RadarSearchListener,BaiduMap.OnMapStatusChangeListener,BDLocationListener,BaiduMap.OnMarkerClickListener, BaiduMap.OnMapClickListener{

    //
    //程序运行相关
    private static boolean isExit = false;// 定义一个变量，来标识是否退出
    Handler handlerExit = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    Handler handlerSearchNearby = null;
    Runnable runnableSearchNearby = null;

    //
    //界面空间相关
    private CustomViewPager mPager;//自定义viewPager，目的是禁用手势滑动
    private List<View> listViews;
    private Button switchBtn;
    private ImageButton btnMapCenter;
    private ImageButton btnLocation;

    private int View_Map = 0;
    private int View_List = 1;
    private int viewIndex = View_Map;
    private Button listPreBtn;
    private Button listNextBtn;
    private TextView listCurPage;
    private Button mapPreBtn;
    private Button mapNextBtn;
    private TextView mapCurPage;

    private boolean isInfoWindowShow = false;

    /* 定位相关 */
    private LocationClient mLocClient;
    private int pageIndex = 0;
    private int curPage = 0;
    private int totalPage = 0;
    private LatLng ptUserLoc = null;
    private boolean isFirstLoc = true;
    private LatLng ptMapCenter = null;
    GeoCoder mGeoSearch = GeoCoder.newInstance();

    //地图相关
    private MapView mMapView = null;
    private BaiduMap mBaiduMap = null;
    private BitmapDescriptor ff3 = BitmapDescriptorFactory.fromResource(R.drawable.icon_marker);
    private BitmapDescriptor iconMapCenter = BitmapDescriptorFactory.fromResource(R.drawable.icon_map_center);
    private Overlay markerCenter = null;

    //周边雷达相关
    private RadarSearchManager mRadarManager = null;
    private RadarNearbyResult listResult = null;
    private ListView mResultListView = null;
    private RadarResultListAdapter mResultListAdapter = null;
    private boolean bUploadRadarInfoStopped = true;//指示自动上传用雷达信息，是否停止
    private int radarRadius = 1000;
    private String userId;
    private String userDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // SDKInitializer.initialize(this);
        setContentView(R.layout.activity_map);

        //获取传过来的参数
        Intent intent = getIntent();
        userId = intent.getStringExtra("username");
        userDes = intent.getStringExtra("note");

        //获取地理位置编码监听
        this.mGeoSearch.setOnGetGeoCodeResultListener(this.getGeoCoderResultListener);

        //初始化UI和地图
        initUI();

        //周边雷达设置监听
        mRadarManager = RadarSearchManager.getInstance();
        mRadarManager.addNearbyInfoListener(this);

        //周边雷达设置用户，id为空默认是设备标识
        startUpLoadRadarInfo();

        // 定位初始化
        mLocClient = new LocationClient(getApplicationContext());
        mLocClient.registerLocationListener(this);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();

        //定时获取周边信息
        handlerSearchNearby = new Handler();
        runnableSearchNearby = new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                searchNearby();
                handlerSearchNearby.postDelayed(this, 30 * 1000);
            }
        };
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
        mMapView.showScaleControl(false);
        mBaiduMap = mMapView.getMap();

        mBaiduMap.setOnMarkerClickListener(this);
        mBaiduMap.setOnMapClickListener(this);
        mBaiduMap.setOnMapStatusChangeListener(this);
        mBaiduMap.setMyLocationEnabled(true);

        listViews.add(mapLayout);
        listViews.add(layout);

        mPager.setAdapter(new MyPagerAdapter(listViews));
        mPager.setCurrentItem(viewIndex);
        mPager.setOnPageChangeListener(new MyOnPageChangeListener());

        switchBtn = (Button)findViewById(R.id.switchButton);
        listPreBtn = (Button)layout.findViewById(R.id.radarlistpre);
        listNextBtn = (Button)layout.findViewById(R.id.radarlistnext);
        listCurPage = (TextView)layout.findViewById(R.id.radarListPage);
        mapPreBtn = (Button)mapLayout.findViewById(R.id.radarmappre);
        mapNextBtn = (Button)mapLayout.findViewById(R.id.radarmapnext);
        mapCurPage = (TextView)mapLayout.findViewById(R.id.radarMapPage);

        this.btnMapCenter = (ImageButton)findViewById(R.id.imageButtonMapCenter);
        this.btnLocation = (ImageButton)findViewById(R.id.imageButtonLocation);

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

    private void startUpLoadRadarInfo(){

        mRadarManager.setUserID(userId);

        this.bUploadRadarInfoStopped = false;

        mRadarManager.startUploadAuto(this, 5000);
    }

    private void stopUpLoadRadarInfo(){
        this.bUploadRadarInfoStopped = true;
        mRadarManager.stopUploadAuto();
    }

    private void searchNearby() {
        if (ptMapCenter == null) {
            Toast.makeText(MapActivity.this, "未获取到位置", Toast.LENGTH_LONG)
                    .show();
            return;
        }
        pageIndex = 0;
        searchRequest(pageIndex);
    }

    private void searchRequest(int index) {

        //如果有信息窗口显示，不进行刷新界面
        if(this.isInfoWindowShow)
            return;

        Log.w("MapActivity","searchRequest----index=" + index);
        curPage = 0;
        totalPage = 0;

        Log.w("MapActivity", "searchRequest----radarRadius=" + radarRadius);

        RadarNearbySearchOption option = new RadarNearbySearchOption().centerPt(ptMapCenter).pageNum(pageIndex).radius(radarRadius);
        mRadarManager.nearbyInfoRequest(option);

        listPreBtn.setVisibility(View.INVISIBLE);
        listNextBtn.setVisibility(View.INVISIBLE);
        mapPreBtn.setVisibility(View.INVISIBLE);
        mapNextBtn.setVisibility(View.INVISIBLE);
        listCurPage.setText("0/0");
        mapCurPage.setText("0/0");

    }

    /**
     * 检验位置信息是否过期
     * @param res
     */
    public void checkResultList(RadarNearbyResult res) {

        Log.w("MapActivity","checkResultList");

        //先停止本地自动上传
        stopUpLoadRadarInfo();

        Date curDate = new Date(System.currentTimeMillis());//获取当前时间

        if (res != null && res.infoList != null && res.infoList.size() > 0) {
            for (int i = res.infoList.size() -1;i>=0 ;i--) {

                Date date = res.infoList.get(i).timeStamp;
                long timeSpan = (curDate.getTime() - date.getTime() )/(1000 * 60 * 60);

                if (timeSpan > 48)
                {
                    Log.w("MapActivity","时间超过48小时" + date.toString());

                    mRadarManager.setUserID(res.infoList.get(i).userID);
                    mRadarManager.clearUserInfo();

                    res.infoList.remove(i);
                }

            }
        }

        //开启本地自动上传
        startUpLoadRadarInfo();

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

        if(bUploadRadarInfoStopped) {
            Log.e("MapActivity", "OnUploadInfoCallback  UploadRadarInfoStopped");
            return null;
        }

        RadarUploadInfo info = new RadarUploadInfo();
        info.comments = this.userId;//+ this.userDes;
        info.pt = ptUserLoc;
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
            checkResultList(listResult);
            parseResultToList(listResult);
            parseResultToMap(listResult);
        } else {
            //获取失败
            curPage = 0;
            totalPage = 0;

            parseResultToList(null);
            parseResultToMap(null);

            Log.w("MapActivity", "search nearby failed");
        }

    }

    @Override
    public void onGetUploadState(RadarSearchError error) {

//        Log.w("MapActivity", "onGetUploadState");
//
//        if (error == RadarSearchError.RADAR_NO_ERROR) {
//            //上传成功
//            Log.w("MapActivity", "Upload Success");
//        } else {
//            //上传失败
//            Log.w("MapActivity", "Upload Failed");
//        }
    }

    @Override
    public void onGetClearInfoState(RadarSearchError error) {
        // TODO Auto-generated method stub
        if (error == RadarSearchError.RADAR_NO_ERROR) {
            //清除成功
            Log.w("MapActivity","清除位置成功");
//            Toast.makeText(MapActivity.this, "清除位置成功", Toast.LENGTH_LONG)
//                    .show();
        } else {
            //清除失败
            Log.w("MapActivity","清除位置失败");
//            Toast.makeText(MapActivity.this, "清除位置失败", Toast.LENGTH_LONG)
//                    .show();
        }
    }

    /**
     * 定位SDK监听函数
     */
    @Override
    public void onReceiveLocation(BDLocation location) {
        // map view 销毁后不在处理新接收的位置
        Log.w("MapActivity", "onReceiveLocation");
        if (location == null || mMapView == null || mBaiduMap == null) {

            Log.w("MapActivity", "location == null || mMapView == null || mBaiduMap == null");
            return;
        }

        ptUserLoc = new LatLng(location.getLatitude(), location.getLongitude());

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

                this.ptMapCenter = ptUserLoc;

                mapToLocation(18);

                handlerSearchNearby.postDelayed(runnableSearchNearby, 2000);
            }

        } else {
            Log.w("MapActivity", "mBaiduMap == null");
        }

    }

    @Override
    public void onMapClick(LatLng point) {
        // TODO Auto-generated method stub
        hideInfoWindow();
    }

    @Override
    public boolean onMapPoiClick(MapPoi poi) {
        // TODO Auto-generated method stub

        return false;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        // TODO Auto-generated method stub
        hideInfoWindow();
        if (marker != null) {
            TextView popupText = new TextView(MapActivity.this);
            popupText.setBackgroundResource(R.drawable.popup);
            popupText.setTextColor(0xFF000000);
            popupText.setText(marker.getExtraInfo().getString("des"));
            mBaiduMap.showInfoWindow(new InfoWindow(popupText, marker.getPosition(), -47));
            this.isInfoWindowShow = true;
//            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(marker.getPosition());
//            mBaiduMap.setMapStatus(update);

            return true;
        } else {
            return false;
        }
    }

    private void hideInfoWindow(){

        mBaiduMap.hideInfoWindow();
        this.isInfoWindowShow = false;
    }

    //viewPager切换
    public void switchClick(View v) {
        if (viewIndex == View_Map) {
            //切换为列表
            viewIndex = View_List;
            //switchBtn.setText("地图");
        } else {
            //切换为地图
            viewIndex = View_Map;
            //switchBtn.setText("列表");
        }
        mPager.setCurrentItem(viewIndex);

    }

    @Override
    public void onMapStatusChangeStart(MapStatus mapStatus) {

        Log.w("MapActivity", "onMapStatusChangeStart");

       // hideInfoWindow();

    }

    @Override
    public void onMapStatusChange(MapStatus mapStatus) {
        Log.w("MapActivity","onMapStatusChange");

    }

    @Override
    public void onMapStatusChangeFinish(MapStatus mapStatus) {

        Log.w("MapActivity","onMapStatusChangeFinish");

//        if (markerCenter != null)
//            markerCenter.remove();

        ptMapCenter = mapStatus.target;
//
//        MarkerOptions option = new MarkerOptions().icon(iconMapCenter).position(ptMapCenter);
//
//        Bundle des = new Bundle();
//
//        des.putString("des", "地图中心点");
//
//        option.extraInfo(des);
//
//        markerCenter = mBaiduMap.addOverlay(option);

        radarRadius = (int)DistanceUtil.getDistance(mapStatus.bound.northeast,ptMapCenter);

        this.searchNearby();

        this.mGeoSearch.reverseGeoCode(new ReverseGeoCodeOption().location(ptMapCenter));
    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int arg0) {
            if (arg0 == View_Map) {
                //切换为地图
                viewIndex = View_Map;
                switchBtn.setText("列表");

                btnMapCenter.setVisibility(View.VISIBLE);
                btnLocation.setVisibility(View.VISIBLE);

            } else {
                //切换为列表
                viewIndex = View_List;
                switchBtn.setText("地图");

                btnMapCenter.setVisibility(View.INVISIBLE);
                btnLocation.setVisibility(View.INVISIBLE);

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
                    desc.setText(String.valueOf(list.get(index).distance) + "米"+ "_没有备注"+ list.get(index).timeStamp.toString());
                } else {
                    desc.setText(String.valueOf(list.get(index).distance) + "米"+ "_"+list.get(index).comments+ list.get(index).timeStamp.toString());
                }

                title.setText(list.get(index).userID);
            }


            return convertView;
        }

        @Override
        public int getCount() {
            if (list == null) {
                return 0;
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

    public void onMyInfoClick(View v){
        Intent intent = new Intent(MapActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    public void onSendMessageClick(View v){
        Intent intent = new Intent(MapActivity.this, SendMessageActivity.class);
        startActivity(intent);
    }

    public void onLocationClick(View v){
        MapStatus mapStatus = mBaiduMap.getMapStatus();
        this.mapToLocation(mapStatus.zoom);
    }

    private void mapToLocation(float zoom) {
        if (ptUserLoc != null) {

            MapStatus mapStatus = new MapStatus.Builder()
                    .target(ptUserLoc).zoom(zoom)
                    .build();

            //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
            MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
            //改变地图状态
            mBaiduMap.setMapStatus(mapStatusUpdate);
        }
    }

    private OnGetGeoCoderResultListener getGeoCoderResultListener = new OnGetGeoCoderResultListener() {
        public void onGetGeoCodeResult(GeoCodeResult result) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                //没有检索到结果
            }
            //获取地理编码结果
        }

        @Override
        public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                //没有找到检索结果
            }
            else{
                if(markerCenter != null){
                    TextView popupText = new TextView(MapActivity.this);
                    popupText.setBackgroundResource(R.drawable.popup);
                    popupText.setTextColor(0xFF000000);
                    popupText.setText(markerCenter.getExtraInfo().getString("des"));
                    Bundle des = new Bundle();
                    des.putString("des", result.getAddress());
                    markerCenter.setExtraInfo(des);
                }

            }

            //获取反向地理编码结果
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            handlerExit.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
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

        mGeoSearch.destroy();

        //停止定时刷新周边
        handlerSearchNearby.removeCallbacks(runnableSearchNearby);

        // 退出时销毁定位
        mLocClient.stop();

        //释放周边雷达相关
        mRadarManager.removeNearbyInfoListener(this);
        mRadarManager.clearUserInfo();
        mRadarManager.destroy();

        //释放地图
        ff3.recycle();
        iconMapCenter.recycle();
        mMapView.onDestroy();
        mBaiduMap = null;

        Log.w("MapActivity","onDestroy");
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
