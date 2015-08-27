using System.Linq;
using Gms.Domain;
using SharpArch.NHibernate.Web.Mvc;

namespace Gms.Web.Mvc.Controllers
{
    using System.Web.Mvc;
    [HandleError]
    [Authorize]
    public class SysLogController : BaseController
    {
        public ActionResult Index()
        {          
            return View();
        }

        public ActionResult List(SysLogQuery query)
        {
            var list = this.SysLogRepository.GetList(query);
            var data = list.Data.Select(c => SysLogModel.From(c)).ToList();
            var result = new { total = list.RecordCount, rows = data };
            return Json(result);
        }

       
    }

    public class SysLogModel
    {
        public SysLogModel(SysLog log)
        {
            this.LogInfo = log.LogInfo;
            this.User = log.Manager != null ? log.Manager.LoginName : "";
            this.CreateTime = log.CreateTime.ToString("yyyy-MM-dd HH:mm:ss");
        }

        /// <summary>
        /// 日志内容
        /// </summary>
        public string LogInfo { get; set; }

        /// <summary>
        /// 操作用户
        /// </summary>
        public string User { get; set; }

        /// <summary>
        /// 时间
        /// </summary>
        public string CreateTime { get; set; }
        
        public static SysLogModel From(SysLog log)
        {
            return new SysLogModel(log);
        }
    }
}
