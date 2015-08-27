using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Gms.Common;

namespace TestPropertyInfo
{
    public class FieldNeedAttribute : System.Attribute
    {
        public FieldNeedAttribute()
        {
            this.DefaultValue = null;
        }
        public FieldNeedAttribute(Object value)
        {
            this.DefaultValue = value;
        }
        public Object DefaultValue { get; set; }
    }

    public enum Sex
    {
        男,女
    }

    public class User : Completion
    {
        public User()
        {
            CreateDateTime = DateTimeEx.Default();
        }

        [FieldNeed]
        public String Name { get; set; }

        [FieldNeed(0)]
        public int Id { get; set; }

        [FieldNeed(9999)]
        public DateTime CreateDateTime { get; set; }

        [FieldNeed]
        public Sex Sex { get; set; }
    }
}
