using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Gms.Common;
using SharpArch.Domain.DomainModel;

namespace Gms.Domain
{
    public class Task:Entity
    {
        /// <summary>
        /// 发布人
        /// </summary>
        public virtual User User { get; set; }

        /// <summary>
        /// 内容
        /// </summary>
        public virtual String Content { get; set; }

        /// <summary>
        /// 任务状态
        /// </summary>
        public virtual TaskStatus Status { get; set; }

        /// <summary>
        /// 持续时间
        /// </summary>
        public virtual int Duration { get; set; }

        /// <summary>
        /// 时间
        /// </summary>
        public virtual DateTime CreateTime { get; set; }
    }

    public class TaskQuery : QueryBase
    {
 
        /// <summary>
        /// 发布人
        /// </summary>
        public int? UserId { get; set; }

        /// <summary>
        /// 发布人
        /// </summary>
        public String UserLoginName { get; set; }

        /// <summary>
        /// 任务状态
        /// </summary>
        public TaskStatus? Status { get; set; }

        /// <summary>
        /// 时间
        /// </summary>
        public Range<DateTime?>  CreateTime { get; set; }
    }

}
