using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FluentNHibernate.Conventions;

namespace Gms.Infrastructure.NHibernateMaps.Conventions
{
    public class ColumnConvention : IColumnConvention
    {
        #region IConvention<IColumnInspector,IColumnInstance> 成员

        public void Apply(FluentNHibernate.Conventions.Instances.IColumnInstance instance)
        {
            var type = instance.EntityType;

        }

        #endregion
    }
}
