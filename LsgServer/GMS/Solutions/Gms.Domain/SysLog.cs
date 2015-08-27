using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Gms.Common;
using SharpArch.Domain.DomainModel;

namespace Gms.Domain
{
    public class SysLog:Entity
    {
        /// <summary>
        /// 日志内容
        /// </summary>
        public virtual String LogInfo { get; set; }

        /// <summary>
        /// 操作用户
        /// </summary>
        public virtual Manager Manager { get; set; }

        /// <summary>
        /// 时间
        /// </summary>
        public virtual DateTime CreateTime { get; set; }
    }

    public class SysLogQuery : QueryBase
    {
        public String LogInfo { get; set; }

        /// <summary>
        /// 操作用户
        /// </summary>
        public String LoginName { get; set; }

        /// <summary>
        /// 时间
        /// </summary>
        public Range<DateTime?>  CreateTime { get; set; }
    }

}
