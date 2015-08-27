namespace Gms.Web.Mvc.Controllers
{
    using System.Web.Mvc;
    [HandleError]
    [Authorize]
    public class HomeController : BaseController
    {
        public ActionResult Index()
        {
            ViewData["SysVersion"] = GetVersion();

            return View(CurrentUser);
        }
        public ActionResult Welcome()
        {
            return View();
        }

        public ActionResult UpdateRecord()
        {
            return View();
        }

    }
}
