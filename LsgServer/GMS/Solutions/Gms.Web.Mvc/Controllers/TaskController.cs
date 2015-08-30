using System;
using System.Drawing;
using System.Linq;
using Gms.Domain;
using SharpArch.NHibernate.Web.Mvc;

namespace Gms.Web.Mvc.Controllers
{
    using System.Web.Mvc;
    [HandleError]
    public class TaskController : BaseController
    {
        /// <summary>
        /// 发布任务
        /// </summary>
        /// <param name="name"></param>
        /// <param name="content"></param>
        /// <param name="duration"></param>
        /// <returns></returns>
        [Transaction]
        public ActionResult Add(string name, string content, int duration)
        {
            try
            {
                Task task = new Task();
                task.User = UserRepository.Get(name);

                if (task.User == null)
                {
                    return JsonError("用户状态异常");
                }

                task.Content = content;
                task.Duration = duration;
                task.Status = TaskStatus.发布中;
                task.CreateTime = DateTime.Now;

                task = TaskRepository.SaveOrUpdate(task);

                return JsonSuccess(task);
            }
            catch (Exception ex)
            {
                return JsonError(ex.Message);
            }

        }

        /// <summary>
        /// 关闭任务
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [Transaction]
        public ActionResult Close(int id)
        {
            try
            {
                Task task = TaskRepository.Get(id);

                if (task == null)
                {
                    return JsonError("任务状态异常");
                }

                TimeSpan span = new TimeSpan(0,task.Duration,0);
                DateTime endTime = task.CreateTime + span;

                if (endTime <= DateTime.Now)
                {
                    task.Status = TaskStatus.完成;
                }
                else
                {
                    task.Status = TaskStatus.关闭;
                }
                
                task = TaskRepository.SaveOrUpdate(task);

                return JsonSuccess(task);
            }
            catch (Exception ex)
            {
                return JsonError(ex.Message);
            }

        }

        /// <summary>
        /// 获取任务
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        public ActionResult Get(int id)
        {
            Task task = TaskRepository.Get(id);

            return JsonSuccess(task);
        }

        /// <summary>
        /// 任务咨询
        /// </summary>
        /// <param name="name">用户登录名</param>
        /// <param name="content"></param>
        /// <param name="taskid"></param>
        /// <returns></returns>
        [Transaction]
        public ActionResult Ask(string name,string content,int taskid)
        {
            User user = UserRepository.Get(name);
            if (user == null)
                return JsonError("用户不存在");

            Task task = TaskRepository.Get(taskid);
            if (task == null)
                return JsonError("任务不存在");
            
            TaskAsk taskAsk = new TaskAsk();
            taskAsk.Task = task;
            taskAsk.User = user;
            taskAsk.Content = content;
            taskAsk.CreateTime = DateTime.Now;

            taskAsk = TaskAskRepository.SaveOrUpdate(taskAsk);

            return JsonSuccess(taskAsk);
        }

        public ActionResult AskList(int taskid)
        {
            var list = TaskAskRepository.GetAll(taskid);

            return JsonSuccess(list);
        }

        /// <summary>
        /// 任务申请
        /// </summary>
        /// <param name="name"></param>
        /// <param name="taskid"></param>
        /// <returns></returns>
        [Transaction]
        public ActionResult Apply(string name, int taskid)
        {
            User user = UserRepository.Get(name);
            if (user == null)
                return JsonError("用户状态异常");

            Task task = TaskRepository.Get(taskid);
            if (task == null)
                return JsonError("任务不存在");

            TaskApply taskApply = new TaskApply();
            taskApply.Task = task;
            taskApply.User = user;
            taskApply.Status = AuditStatus.等待审核;
            taskApply.CreateTime = DateTime.Now;

            taskApply = TaskApplyRepository.SaveOrUpdate(taskApply);

            return JsonSuccess(taskApply);
        }

        public ActionResult ApplyList(int taskid)
        {
            var list = TaskApplyRepository.GetAll(taskid);

            return JsonSuccess(list);
        }


        /// <summary>
        /// 审核申请
        /// </summary>
        /// <param name="applyid"></param>
        /// <param name="status"></param>
        /// <param name="reason"></param>
        /// <returns></returns>
        [Transaction]
        public ActionResult Audit(int applyid, int status, string reason)
        {
            TaskApply taskApply = TaskApplyRepository.Get(applyid);
            if (taskApply == null)
                return JsonError("申请不存在");

            taskApply.Status = (AuditStatus)status;
            taskApply.AuditReason = reason;
            taskApply.AuditTime = DateTime.Now;

            taskApply = TaskApplyRepository.SaveOrUpdate(taskApply);

            return JsonSuccess(taskApply);
        }

        /// <summary>
        /// 获取用户任务列表
        /// </summary>
        /// <param name="query"></param>
        /// <returns></returns>
        public ActionResult List(TaskQuery query)
        {
            var list = this.TaskRepository.GetList(query);

            return JsonSuccess(list.Data);
        }


    }
}
