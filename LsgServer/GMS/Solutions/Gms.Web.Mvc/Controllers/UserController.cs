using System;
using System.Drawing;
using System.Linq;
using System.Web.Security;
using Castle.Components.DictionaryAdapter.Xml;
using Gms.Common;
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

                return JsonSuccess(UserModel.From(user));
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

                return JsonSuccess(UserModel.From(user));
            }
            catch (Exception ex)
            {
                return JsonError(ex.Message);
            }
        }

        #region 用户评价
        /// <summary>
        /// 用户评价
        /// </summary>
        /// <returns></returns>
        [Transaction]
        public ActionResult Eval(UserEvalModel eval)
        {
            try
            {
                Task task = TaskRepository.Get(eval.TaskId);
                if (task == null)
                    return JsonError("任务不存在");

                User userFrom = UserRepository.Get(eval.FromUserId);
                if (userFrom == null)
                    return JsonError("评价人不存在");

                User userTo = UserRepository.Get(eval.ToUserId);
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
                userEval.Content = eval.Content;
                userEval.Level = eval.Level;
                userEval.CreateTime = DateTime.Now;

                userEval = UserEvalRepository.SaveOrUpdate(userEval);

                return JsonSuccess(UserEvalModel.From(userEval));
            }
            catch (Exception ex)
            {
                return JsonError(ex.Message);
            }
        }

        public ActionResult EvalList(UserEvalQuery query)
        {
            var list = UserEvalRepository.GetList(query);
            var data = list.Data.Select(c=>UserEvalModel.From(c));
            return JsonSuccess(data);
        }

        #endregion

    }

    public class UserModel
    {
        public UserModel(User user)
        {
            this.Id = user.Id;
            this.LoginName = user.LoginName;
            this.RealName = user.RealName;
            this.NickName = user.NickName;
            this.Gender = user.Gender.ToString();
            this.Mobile = user.Mobile;
            this.Level = user.Level;
            this.Points = user.Points;
            this.CallTimes = user.CallTimes;
            this.RespondTimes = user.RespondTimes;
            this.ReceiveTimes = user.ReceiveTimes;
            this.Intro = user.Intro;
            this.Note = user.Note;
            this.CreateTime = user.CreateTime.ToJsonString();
        }

        public int Id { get; set; }

        /// <summary>
        /// 登录名
        /// </summary>
        public String LoginName { get; set; }

        /// <summary>
        /// 真实姓名
        /// </summary>
        public String RealName { get; set; }

        /// <summary>
        /// 昵称
        /// </summary>
        public String NickName { get; set; }

        /// <summary>
        /// 性别
        /// </summary>
        public String Gender { get; set; }

        /// <summary>
        /// 手机号码
        /// </summary>
        public String Mobile { get; set; }

        /// <summary>
        /// 等级
        /// </summary>
        public int Level { get; set; }

        /// <summary>
        /// 积分
        /// </summary>
        public int Points { get; set; }

        /// <summary>
        /// 发布任务次数
        /// </summary>
        public int CallTimes { get; set; }

        /// <summary>
        /// 响应任务次数
        /// </summary>
        public int RespondTimes { get; set; }

        /// <summary>
        /// 执行任务次数
        /// </summary>
        public int ReceiveTimes { get; set; }

        /// <summary>
        /// 自我介绍
        /// </summary>
        public String Intro { get; set; }
        
        /// <summary>
        /// 备注
        /// </summary>
        public String Note { get; set; }

        /// <summary>
        /// 创建时间
        /// </summary>
        public String CreateTime { get; set; }

        public static UserModel From(User user)
        {
            return new UserModel(user);
        }
    }

    public class UserEvalModel
    {
        public UserEvalModel(UserEval userEval)
        {
            this.Id = userEval.Id;
            if (userEval.Task != null)
                this.TaskId = userEval.Task.Id;

            if (userEval.ToUser != null)
            {
                this.ToUserId = userEval.ToUser.Id;
                this.ToUserLoginName = userEval.ToUser.LoginName;
            }
            
            this.UserRole = userEval.UserRole.ToString();

            if (userEval.FromUser != null)
            {
                this.FromUserId = userEval.FromUser.Id;
                this.FromUserLoginName = userEval.FromUser.LoginName;
            }

            this.Content = userEval.Content;
            this.Level = userEval.Level;
            this.CreateTime = userEval.CreateTime.ToJsonString();
        }

        public int Id { get; set; }

        public int TaskId { get; set; }

        /// <summary>
        /// 被评价人
        /// </summary>
        public int ToUserId { get; set; }
        public String ToUserLoginName { get; set; }

        /// <summary>
        /// 被评价人的任务角色
        /// </summary>
        public String UserRole { get; set; }

        /// <summary>
        /// 评价人
        /// </summary>
        public int FromUserId { get; set; }
        public String FromUserLoginName { get; set; }

        /// <summary>
        /// 评价内容
        /// </summary>
        public String Content { get; set; }

        /// <summary>
        /// 评价星级
        /// </summary>
        public int Level { get; set; }

        /// <summary>
        /// 时间
        /// </summary>
        public String CreateTime { get; set; }

        public static UserEvalModel From(UserEval userEval)
        {
            return new UserEvalModel(userEval);
        }

    }
}
