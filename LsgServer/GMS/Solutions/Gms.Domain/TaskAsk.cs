using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Gms.Common;
using SharpArch.Domain.DomainModel;

namespace Gms.Domain
{
    public class TaskAsk:Entity
    {
        /// <summary>
        /// 任务
        /// </summary>
        public virtual Task Task { get; set; }

        /// <summary>
        /// 咨询人
        /// </summary>
        public virtual User User { get; set; }

        /// <summary>
        /// 咨询内容
        /// </summary>
        public virtual String Content { get; set; }

        /// <summary>
        /// 时间
        /// </summary>
        public virtual DateTime CreateTime { get; set; }
    }

    public class TaskAskQuery : QueryBase
    {
        /// <summary>
        /// 任务
        /// </summary>
        public int? TaskId { get; set; }

        /// <summary>
        /// 咨询人
        /// </summary>
        public int? UserId { get; set; }

        /// <summary>
        /// 咨询人
        /// </summary>
        public String UserLoginName { get; set; }

        /// <summary>
        /// 时间
        /// </summary>
        public Range<DateTime?>  CreateTime { get; set; }
    }

}
