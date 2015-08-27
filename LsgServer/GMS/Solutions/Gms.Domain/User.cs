using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Gms.Common;
using SharpArch.Domain.DomainModel;

namespace Gms.Domain
{
    public class User : Entity
    {
        public User()
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
        /// 等级
        /// </summary>
        public virtual int Level { get; set; }

        /// <summary>
        /// 积分
        /// </summary>
        public virtual int Points { get; set; }

        /// <summary>
        /// 发布任务次数
        /// </summary>
        public virtual int CallTimes { get; set; }

        /// <summary>
        /// 响应任务次数
        /// </summary>
        public virtual int RespondTimes { get; set; }

        /// <summary>
        /// 执行任务次数
        /// </summary>
        public virtual int ReceiveTimes { get; set; }

        /// <summary>
        /// 自我介绍
        /// </summary>
        public virtual String Intro { get; set; }

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

    public class UserQuery : QueryBase
    {

    }
}
