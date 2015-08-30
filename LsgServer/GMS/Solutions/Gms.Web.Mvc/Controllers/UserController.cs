using System;
using System.Drawing;
using System.Linq;
using System.Web.Security;
using Gms.Domain;
using SharpArch.NHibernate.Web.Mvc;

namespace Gms.Web.Mvc.Controllers
{
    using System.Web.Mvc;
    [HandleError]
    public class UserController : BaseController
    {
        /// <summary>
        /// 用户注册
        /// </summary>
        /// <param name="mobile"></param>
        /// <param name="psw"></param>
        /// <returns></returns>
        [Transaction]
        public ActionResult Register(string mobile, string psw)
        {
            try
            {
                string strUserName = mobile.Trim();

                MembershipUser membershipuser = Membership.GetUser(strUserName);

                if (membershipuser != null)
                {
                    throw new Exception("该手机号已经注册!");
                }

                membershipuser = Membership.CreateUser(strUserName, psw);

                User user = new User();
                user.LoginName = mobile;
                user.Mobile = mobile;
                user.MemberShipId = (Guid)membershipuser.ProviderUserKey;

                user = this.UserRepository.SaveOrUpdate(user);

                return JsonSuccess(user);
            }
            catch (Exception ex)
            {
                return JsonError(ex.Message);
            }
        }

        /// <summary>
        /// 修改登录密码
        /// </summary>
        /// <param name="name"></param>
        /// <param name="psw"></param>
        /// <returns></returns>
        public ActionResult CngPsw(string name, string psw)
        {
            try
            {
                string strUserName = name.Trim();

                MembershipUser membershipuser = Membership.GetUser(strUserName);

                if (membershipuser == null)
                {
                    throw new Exception("用户不存在");
                }

                string old = membershipuser.ResetPassword();

                membershipuser.ChangePassword(old, psw);

                return JsonSuccess(String.Format("修改成功密码【{0}】", psw));
            }
            catch (Exception ex)
            {
                return JsonError(ex.Message);
            }
        }

        /// <summary>
        /// 更新用户信息
        /// </summary>
        /// <param name="user"></param>
        /// <returns></returns>
        [Transaction]
        public ActionResult UpdateInfo(User user)
        {
            try
            {
                user = this.UserRepository.Get(user.Id);

                TryUpdateModel(user);

                user = this.UserRepository.SaveOrUpdate(user);

                return JsonSuccess(user);
            }
            catch (Exception ex)
            {
                return JsonError(ex.Message);
            }
        }

        /// <summary>
        /// 用户评价
        /// </summary>
        /// <param name="from"></param>
        /// <param name="to"></param>
        /// <param name="taskid"></param>
        /// <param name="content"></param>
        /// <param name="level"></param>
        /// <returns></returns>
        [Transaction]
        public ActionResult Eval(string from,string to,int taskid,string content,int level)
        {
            try
            {
                Task task = TaskRepository.Get(taskid);
                if (task == null)
                    return JsonError("任务不存在");

                User userFrom = UserRepository.Get(from);
                if (userFrom == null)
                    return JsonError("评价人不存在");

                User userTo = UserRepository.Get(to);
                if (userTo == null)
                    return JsonError("被评价人不存在");

                UserEval userEval = new UserEval();
                userEval.Task = task;

                if (task.User.LoginName.Equals(userTo.LoginName))
                {
                    userEval.UserRole = UserRole.雇主;
                }
                else
                {
                    userEval.UserRole = UserRole.雇员;
                }

                userEval.FromUser = userFrom;
                userEval.ToUser = userTo;
                userEval.Content = content;
                userEval.Level = level;
                userEval.CreateTime = DateTime.Now;

                userEval = UserEvalRepository.SaveOrUpdate(userEval);

                return JsonSuccess(userEval);
            }
            catch (Exception ex)
            {
                return JsonError(ex.Message);
            }
        }

        public ActionResult EvalList(string name)
        {
            var list = UserEvalRepository.GetAll(name);
            return JsonSuccess(list);
        }



    }
}
