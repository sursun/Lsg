using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Gms.Common;
using SharpArch.Domain.DomainModel;

namespace Gms.Domain
{
    public class Manager : Entity
    {
        public Manager()
        {
            this.CreateTime = DateTime.Now;
        }

        /// <summary>
        /// 登录名
        /// </summary>
        public virtual String LoginName { get; set; }

        /// <summary>
        /// 
        /// </summary>
        public virtual Guid MemberShipId { get; set; }

        /// <summary>
        /// 真实姓名
        /// </summary>
        public virtual String RealName { get; set; }

        /// <summary>
        /// 昵称
        /// </summary>
        public virtual String NickName { get; set; }

        /// <summary>
        /// 性别
        /// </summary>
        public virtual Gender Gender { get; set; }

        /// <summary>
        /// 手机号码
        /// </summary>
        public virtual String Mobile { get; set; }
        
        /// <summary>
        /// 启用|禁用
        /// </summary>
        public virtual Enabled Enabled { get; set; }

        /// <summary>
        /// 备注
        /// </summary>
        public virtual String Note { get; set; }

        /// <summary>
        /// 创建时间
        /// </summary>
        public virtual DateTime CreateTime { get; set; }
    }

    public class ManagerQuery : QueryBase
    {

    }
}
