using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Gms.Common;
using SharpArch.Domain.DomainModel;

namespace Gms.Domain
{
    /// <summary>
    /// 用户评价
    /// </summary>
    public class UserEval : Entity
    {
        /// <summary>
        /// 被评价人
        /// </summary>
        public virtual User ToUser { get; set; }

        /// <summary>
        /// 任务角色
        /// </summary>
        public virtual UserRole UserRole { get; set; }

        /// <summary>
        /// 评价人
        /// </summary>
        public virtual User FromUser { get; set; }

        /// <summary>
        /// 评价内容
        /// </summary>
        public virtual String Content { get; set; }

        /// <summary>
        /// 评价星级
        /// </summary>
        public virtual int Level { get; set; }

        /// <summary>
        /// 时间
        /// </summary>
        public virtual DateTime CreateTime { get; set; }
    }

    public class UserEvalQuery : QueryBase
    {
 
        /// <summary>
        /// 被评价人
        /// </summary>
        public int? ToUserId { get; set; }

        /// <summary>
        /// 被评价人
        /// </summary>
        public String ToUserLoginName { get; set; }

        /// <summary>
        /// 任务角色
        /// </summary>
        public UserRole? UserRole { get; set; }

        /// <summary>
        /// 评价人
        /// </summary>
        public int? FromUserId { get; set; }

        /// <summary>
        /// 评价人
        /// </summary>
        public String FromUserLoginName { get; set; }
        
        /// <summary>
        /// 时间
        /// </summary>
        public Range<DateTime?>  CreateTime { get; set; }
    }

}
