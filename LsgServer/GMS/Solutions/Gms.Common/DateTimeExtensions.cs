using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Gms.Common
{
    public static class DateTimeEx
    {
        public static DateTime Default()
        {
            return new DateTime(9999,1,1);
        }
    }

    public static class DateTimeExtensions
    {
        public static string ToJsonString(this DateTime dateTime)
        {
            return dateTime.ToString("yyyy-MM-dd HH:mm:ss");
        }
    }
}
