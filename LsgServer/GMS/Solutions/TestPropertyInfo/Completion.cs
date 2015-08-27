using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text;

namespace TestPropertyInfo
{
    public class Completion
    {
        public int Total { get; set; }
        public int Completed { get; set; }

        public void Computer()
        {
            Type type = this.GetType();
            PropertyInfo[] props = type.GetProperties();
            foreach (PropertyInfo p in props)
            {
                object[] attrs = p.GetCustomAttributes(typeof (FieldNeedAttribute), false);
                if (attrs.Length != 0)
                {
                    this.Total++;

                    if (((FieldNeedAttribute) attrs[0]).DefaultValue == null)
                    {
                        if (p.GetValue(this, null) != null)
                        {
                            this.Completed++;
                            Console.WriteLine(string.Format("PropertyInfo--{0}-{1}", p.Name, p.GetValue(this, null)));
                        }
                        else
                        {
                            Console.WriteLine("PropertyInfo---:" + p.Name);
                        }
                    }
                    else
                    {
                        Type attrType = p.PropertyType;
                        Object val = ((FieldNeedAttribute) attrs[0]).DefaultValue;
                        Object objVal =p.GetValue(this, null);
                        

                        if (attrType.FullName.Equals("System.DateTime"))
                        {
                            int year = (int)val;
                            DateTime timeDest = (DateTime) objVal;

                            if (year != timeDest.Year)
                            {
                                this.Completed++;
                            }

                            Console.WriteLine("PropertyInfo DateTime:" + p.Name);
                        }
                        else
                        {
                            Console.WriteLine(string.Format("PropertyInfo {0}-{1}", attrType.FullName, p.Name));

                            if (!val.Equals(objVal))
                            {
                                this.Completed++;
                            }
                        }
                    }
                }
            }
        }
    }
}
