using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Web;
using System.Web.Mvc;
using System.Web.Security;
using Gms.Common;
using Gms.Domain;
using Gms.Infrastructure;
using Newtonsoft.Json;
using Newtonsoft.Json.Serialization;
using NHibernate.Proxy;
using SharpArch.Web.Mvc.JsonNet;

namespace Gms.Web.Mvc.Controllers
{
    public class BaseController : Controller
    {
        public IUserRepository UserRepository { get; set; }
        public IManagerRepository ManagerRepository { get; set; }
        public IDepartmentRepository DepartmentRepository { get; set; }
        public ICommonCodeRepository CommonCodeRepository { get; set; }
        public ISysLogRepository SysLogRepository { get; set; }


        private string _SysVersion { get; set; }

        public string GetVersion()
        {
            if (_SysVersion.IsNullOrEmpty())
            {
                _SysVersion = System.Reflection.Assembly.GetExecutingAssembly().GetName().Version.ToString();
            }

            return _SysVersion;
        }

        public bool IsSystem()
        {
            return this.HttpContext.User.IsInRole(RoleConstants.SYSTEM);
        }

        public Guid MembershipId
        {
            get
            {

                if (HttpContext.User.Identity.IsAuthenticated)
                {
                    var item = this.HttpContext.User.Identity as FormsIdentity;

                    return new Guid(item.Ticket.UserData);
                }

                return Guid.Empty;
            }
        }

        private Manager _CurrentUser;

        public Manager CurrentUser
        {
            get
            {
                if (MembershipId == Guid.Empty)
                {
                    return null;
                }
                if (_CurrentUser == null)
                {
                    _CurrentUser = ManagerRepository.Get(MembershipId);
                }
                return _CurrentUser;
            }
        }
        

        /// <summary>
        /// This provides simple feedback to the modelstate in the case of errors.
        /// </summary>
        /// <param name="filterContext"></param>
        protected override void OnActionExecuted(ActionExecutedContext filterContext)
        {
            if (filterContext.Result is RedirectToRouteResult)
            {
                try
                {
                    // put the ModelState into TempData
                    TempData.Add("_MODELSTATE", ModelState);
                }
                catch (Exception)
                {
                    TempData.Clear();
                    // swallow exception
                }
            }
            else if (filterContext.Result is ViewResult && TempData.ContainsKey("_MODELSTATE"))
            {
                // merge modelstate from TempData
                var modelState = TempData["_MODELSTATE"] as ModelStateDictionary;
                foreach (var item in modelState)
                {
                    if (!ModelState.ContainsKey(item.Key))
                        ModelState.Add(item);
                }
            }
            base.OnActionExecuted(filterContext);
        }

        #region Result
        protected JsonResult JsonSuccess<T>(T data)
        {
            return Json(new { success = true, data = data }, JsonRequestBehavior.AllowGet);
        }
        public JsonResult JsonSuccess()
        {
            return Json(new { success = true }, JsonRequestBehavior.AllowGet);
        }
        protected JsonResult JsonError(string error)
        {
            return Json(new { success = false, data = error }, JsonRequestBehavior.AllowGet);
        }

        protected ActionResult JsonNet<T>(T obj)
        {
            var result = new JsonNetResult(obj);

            result.Formatting = Formatting.None;
            result.SerializerSettings.ReferenceLoopHandling = ReferenceLoopHandling.Ignore;
            result.SerializerSettings.ContractResolver = new NHibernateContractResolver();

            result.SerializerSettings.Converters.Add(new Newtonsoft.Json.Converters.JavaScriptDateTimeConverter());
            return result;

        }

        public ActionResult JsonNetSuccess<T>(T data)
        {
            return JsonNet(new { success = true, data = data });
        }
        public ActionResult JsonNetnError(string error)
        {
            return JsonNet(new { success = false, data = error });
        }

        protected ActionResult Empty()
        {
            return new EmptyResult();
        }

       

        #endregion
    }

    public class NHibernateContractResolver : DefaultContractResolver
    {
        protected override List<MemberInfo> GetSerializableMembers(Type objectType)
        {
            if (typeof(INHibernateProxy).IsAssignableFrom(objectType))
            {
                return base.GetSerializableMembers(objectType.BaseType);
            }
            else
            {
                return base.GetSerializableMembers(objectType);
            }
        }
    }

    public class AutocompleteItem
    {
        public String label { get; set; }
        public String value { get; set; }
        public String param1 { get; set; }
        public String param2 { get; set; }
    }
}
