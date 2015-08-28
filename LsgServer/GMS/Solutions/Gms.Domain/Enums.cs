using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Gms.Domain
{

    public enum Gender
    {
        男,
        女
    }

    public enum Enabled
    {
        启用,
        禁用
    }
    
    public enum YesNo
    {
        否,
        是
    }

    public enum TaskStatus
    {
        发布中,
        关闭,
        完成
    }

    public enum AuditStatus
    {
        等待审核,
        审核通过,
        拒绝
    }

    public enum UserRole
    {
        雇主,
        雇员
    }

    public enum CommonCodeType
    {
        没有,
        其他
    }
}

