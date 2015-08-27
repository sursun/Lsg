using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TestPropertyInfo
{
    public class Doctor:User
    {
        [FieldNeed]
        public String CodeoNo { get; set; }

        public String Duty { get; set; }

    }
}
