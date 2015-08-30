using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Gms.Common;
using SharpArch.Domain.DomainModel;

namespace Gms.Domain
{
    /// <summary>
    /// 任务申请
    /// </summary>
    public class TaskApply: Entity
    {
        /// <summary>
        /// 任务
        /// </summary>
        public virtual Task Task { get; set; }

        /// <summary>
        /// 申请人
        /// </summary>
        public virtual User User { get; set; }

        /// <summary>
        /// 审核理由
        /// </summary>
        public virtual String AuditReason { get; set; }

        /// <summary>
        /// 审核时间
        /// </summary>
        public virtual DateTime AuditTime { get; set; }

        /// <summary>
        /// 审核状态
        /// </summary>
        public virtual AuditStatus Status { get; set; }

        /// <summary>
        /// 时间
        /// </summary>
        public virtual DateTime CreateTime { get; set; }
    }

    public class TaskApplyQuery : QueryBase
    {
        /// <summary>
        /// 任务
        /// </summary>
        public int? TaskId { get; set; }

        /// <summary>
        /// 申请人
        /// </summary>
        public int? UserId { get; set; }

        /// <summary>
        /// 申请人
        /// </summary>
        public String UserLoginName { get; set; }

        /// <summary>
        /// 审核状态
        /// </summary>
        public AuditStatus? Status { get; set; }

        /// <summary>
        /// 时间
        /// </summary>
        public Range<DateTime?>  CreateTime { get; set; }
    }

}
