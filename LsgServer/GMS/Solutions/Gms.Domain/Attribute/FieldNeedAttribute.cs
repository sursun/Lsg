using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;


namespace Gms.Domain.Attribute
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
}
