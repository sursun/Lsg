using System;
using System.Drawing;
using System.Linq;
using Gms.Common;
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

                return JsonSuccess(TaskModel.From(task));
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

                return JsonSuccess(TaskModel.From(task));
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

            return JsonSuccess(TaskModel.From(task));
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

            return JsonSuccess(TaskAskModel.From(taskAsk));
        }

        public ActionResult AskList(int taskid)
        {
            var list = TaskAskRepository.GetAll(taskid).Select(c=>TaskAskModel.From(c));

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

            return JsonSuccess(TaskApplyModel.From(taskApply));
        }

        public ActionResult ApplyList(int taskid)
        {
            var list = TaskApplyRepository.GetAll(taskid).Select(c => TaskApplyModel.From(c));

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

            return JsonSuccess(TaskApplyModel.From(taskApply));
        }

        /// <summary>
        /// 获取用户任务列表
        /// </summary>
        /// <param name="query"></param>
        /// <returns></returns>
        public ActionResult List(TaskQuery query)
        {
            var list = this.TaskRepository.GetList(query);
            var data = list.Data.Select(c => TaskModel.From(c));

            return JsonSuccess(data);
        }


    }

    public class TaskModel
    {
        public TaskModel(Task task)
        {
            this.Id = task.Id;
            if (task.User != null)
            {
                this.UserId = task.User.Id;
                this.UserLoginName = task.User.LoginName;
            }

            this.Content = task.Content;
            this.Status = task.Status.ToString();
            this.Duration = task.Duration;
            this.CreateTime = task.CreateTime.ToJsonString();
        }

        public int Id { get; set; }

        /// <summary>
        /// 发布人
        /// </summary>
        public int UserId { get; set; }
        public String UserLoginName { get; set; }

        /// <summary>
        /// 内容
        /// </summary>
        public String Content { get; set; }

        /// <summary>
        /// 任务状态
        /// </summary>
        public String Status { get; set; }

        /// <summary>
        /// 持续时间
        /// </summary>
        public int Duration { get; set; }

        /// <summary>
        /// 时间
        /// </summary>
        public String CreateTime { get; set; }

        public static TaskModel From(Task task)
        {
            return new TaskModel(task);
        }
    }

    public class TaskAskModel
    {
        public TaskAskModel(TaskAsk taskAsk)
        {
            this.Id = taskAsk.Id;
            if (taskAsk.Task != null)
                this.TaskId = taskAsk.Id;
            if (taskAsk.User != null)
            {
                this.UserId = taskAsk.Id;
                this.UserLoginName = taskAsk.User.LoginName;
            }
            this.Content = taskAsk.Content;
            this.CreateTime = taskAsk.CreateTime.ToJsonString();
        }

        public int Id { get; set; }

        /// <summary>
        /// 任务
        /// </summary>
        public int TaskId { get; set; }

        /// <summary>
        /// 咨询人
        /// </summary>
        public int UserId { get; set; }
        public String UserLoginName { get; set; }

        /// <summary>
        /// 咨询内容
        /// </summary>
        public String Content { get; set; }

        /// <summary>
        /// 时间
        /// </summary>
        public String CreateTime { get; set; }

        public static TaskAskModel From(TaskAsk taskAsk)
        {
            return new TaskAskModel(taskAsk);
        }

    }

    public class TaskApplyModel
    {
        public TaskApplyModel(TaskApply taskApply)
        {
            this.Id = taskApply.Id;
            if (taskApply.Task != null)
                this.TaskId = taskApply.Task.Id;

            if (taskApply.User != null)
            {
                this.UserId = taskApply.User.Id;
                this.UserLoginName = taskApply.User.LoginName;
            }

            this.AuditReason = taskApply.AuditReason;
            this.AuditTime = taskApply.AuditTime.ToJsonString();
            this.Status = taskApply.Status.ToString();
            this.CreateTime = taskApply.CreateTime.ToJsonString();
        }

        public int Id { get; set; }
        
        /// <summary>
        /// 任务
        /// </summary>
        public int TaskId { get; set; }

        /// <summary>
        /// 申请人
        /// </summary>
        public int UserId { get; set; }
        public String UserLoginName { get; set; }

        /// <summary>
        /// 审核理由
        /// </summary>
        public String AuditReason { get; set; }

        /// <summary>
        /// 审核时间
        /// </summary>
        public String AuditTime { get; set; }

        /// <summary>
        /// 审核状态
        /// </summary>
        public String Status { get; set; }

        /// <summary>
        /// 时间
        /// </summary>
        public String CreateTime { get; set; }

        public static TaskApplyModel From(TaskApply taskApply)
        {
            return new TaskApplyModel(taskApply);
        }

    }

}
