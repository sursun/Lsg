using System;
using System.Collections.Generic;
using System.Data;
using System.Web.Security;
using Gms.Domain;
using Gms.Infrastructure;
using Gms.Infrastructure.NHibernateMaps;
using NHibernate.Cfg;
using NHibernate.Tool.hbm2ddl;
using NUnit.Framework;
using SharpArch.NHibernate;
using SharpArch.Testing.NUnit.NHibernate;

namespace Gms.Tests.Gms.Data
{

   [TestFixture]
    [Category("DB Tests")]
    public class BaseDataTest
    {
        private Configuration configuration;

        [SetUp]
        public virtual void SetUp()
        {
            string[] mappingAssemblies = RepositoryTestsHelper.GetMappingAssemblies();
            this.configuration = NHibernateSession.Init(
                new SimpleSessionStorage(),
                mappingAssemblies,
                new AutoPersistenceModelGenerator().Generate(),
                "../../../../Solutions/Gms.Web.Mvc/NHibernate.config");

            IDbConnection connection = NHibernateSession.Current.Connection;
            new SchemaExport(configuration).Execute(false, true, false, connection, null);
        }

        [TearDown]
        public virtual void TearDown()
        {
            NHibernateSession.CloseAllSessions();
            NHibernateSession.Reset();
        }

       [Test]
       public void InitData()
       {
           //var depart = CreateDepartment();

           //CreateDoctor(depart);

           //CreatePatient();

           InitCommonCode();

           NHibernateSession.Current.Flush();
       }

       [Test]
       public Department CreateDepartment()
       {
           IDepartmentRepository departmentRepository = new DepartmentRepository();
           var deppart = departmentRepository.SaveOrUpdate(new Department()
           {
               Name = "内分泌科"
           });

           departmentRepository.SaveOrUpdate(new Department()
           {
               Parent = deppart,
               Name = "内分泌一组"
           });

           return departmentRepository.SaveOrUpdate(new Department()
           {
               Parent = deppart,
               Name = "内分泌二组"
           });
       }
       
       public void CreateDoctor(Department department)
       {
           IDoctorRepository doctorRepository = new DoctorRepository();

           for (int i = 0; i < 100; i++)
           {
               Doctor doctor = new Doctor();
               doctor.Sex = Sex.男;
               doctor.Department = department;
               doctor.CodeNo = String.Format("10{0:D3}", i);
               doctor.LoginName = doctor.CodeNo;
               doctor.RealName = String.Format("李{0}测试", i);
               doctor.CreateTime = DateTime.Now;

               MembershipUser membershipuser = Membership.CreateUser(doctor.LoginName, "123");
               doctor.MemberShipId = (Guid)membershipuser.ProviderUserKey;
               
               doctorRepository.SaveOrUpdate(doctor);
           }
       }

       public void CreatePatient()
       {
           IPatientRepository patientRepository = new PatientRepository();
           patientRepository.SaveOrUpdate(new Patient()
           {
               RealName = "朱泽洋"
               ,
               IdCard = "51170219740419175X"
               ,
               Birthday = new DateTime(1974,4,19)
           });
       }

      
        public void InitCommonCode()
       {
            ICommonCodeRepository commonCodeRepository = new CommonCodeRepository();
           
            //地区
           //commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.地区, Name = "北京"});
           //commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.地区, Name = "天津" });
           //commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.地区, Name = "上海" });
           //commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.地区, Name = "重庆" });
           //var parent = commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.地区, Name = "山东" });
           //commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.地区,Parent = parent,Name = "济南" });
           //commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.地区, Parent = parent,Name = "青岛" });
           //commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.地区, Parent = parent,Name = "淄博" });
           //commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.地区, Parent = parent,Name = "枣庄" });
           //commonCodeRepository.SaveOrUpdate(new CommonCode() {Type = CommonCodeType.地区, Parent = parent, Name = "泰安"});

           //民族
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "汉族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "壮族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "满族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "回族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "苗族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "维吾尔族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "土家族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "彝族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "蒙古族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "藏族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "布依族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "侗族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "瑶族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "朝鲜族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "白族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "哈尼族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "哈萨克族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "黎族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "傣族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "畲族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "傈僳族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "仡佬族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "东乡族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "高山族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "拉祜族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "水族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "佤族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "纳西族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "羌族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "土族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "仫佬族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "锡伯族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "柯尔克孜族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "达斡尔族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "景颇族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "毛南族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "撒拉族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "布朗族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "塔吉克族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "阿昌族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "普米族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "鄂温克族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "怒族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "京族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "基诺族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "德昂族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "保安族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "俄罗斯族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "裕固族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "乌孜别克族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "门巴族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "鄂伦春族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "独龙族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "塔塔尔族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "赫哲族" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.民族, Name = "珞巴族" });
   
           //糖尿病类型
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.糖尿病类型, Name = "1型糖尿病" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.糖尿病类型, Name = "2型糖尿病" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.糖尿病类型, Name = "正常耐糖量" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.糖尿病类型, Name = "妊娠糖尿病" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.糖尿病类型, Name = "特殊类型糖尿病" });
   
           //职业
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "在校学生" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "计算机/网络/IT技术" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "经营管理" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "娱乐业" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "文体工作" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "销售" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "医疗卫生" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "农林牧渔劳动者" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "酒店/餐饮/旅游/其他服务" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "美术/设计/创意" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "电子/电器/通信技术" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "外出务工人员" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "贸易/物流/采购/运输" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "建筑/房地产/装饰装修/物业管理" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "财务/审计/统计" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "电气/能源/动力" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "个体经营/商业零售" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "军人/警察" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "美容保健" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "行政/后勤" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "教育/培训" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "党政机关事业单位工作者/公务员类" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "市场/公关/咨询/媒介" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "技工" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "工厂生产" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "宗教/神职人员" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "工程师" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "新闻出版/文化工作" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "金融" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "人力资源" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "保险" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "法律" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "翻译" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "自由职业者" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "待业/无业/失业" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "退休" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "干部" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.职业, Name = "其他" });

           //教育水平
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.教育水平, Name = "小学及以下" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.教育水平, Name = "初中" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.教育水平, Name = "高中" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.教育水平, Name = "中专" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.教育水平, Name = "大专" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.教育水平, Name = "本科" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.教育水平, Name = "硕士" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.教育水平, Name = "博士及以上" });
           commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.教育水平, Name = "博士后" });

           //药品类别
           //commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.药品类别, Name = "降压药" });
           //commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.药品类别, Name = "降糖药" });
           //commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.药品类别, Name = "降脂药" });
           //commonCodeRepository.SaveOrUpdate(new CommonCode() { Type = CommonCodeType.药品类别, Name = "其他" });

       }


    }
}
