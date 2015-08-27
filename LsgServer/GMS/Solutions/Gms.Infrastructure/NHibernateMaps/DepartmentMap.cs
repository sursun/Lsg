using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FluentNHibernate.Automapping.Alterations;
using Gms.Domain;

namespace Gms.Infrastructure.NHibernateMaps
{
    public class DepartmentMap : IAutoMappingOverride<Department>
    {
        public void Override(FluentNHibernate.Automapping.AutoMapping<Department> mapping)
        {
            mapping.HasMany(c => c.Subs).KeyColumn("ParentFk").ForeignKeyConstraintName("fk_Department_ParentDepartment").LazyLoad().Cascade.All();

        }
    }
}
