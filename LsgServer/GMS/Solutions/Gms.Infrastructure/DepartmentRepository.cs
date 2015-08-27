using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Gms.Domain;

namespace Gms.Infrastructure
{
    public class DepartmentRepository : RepositoryBase<Department>, IDepartmentRepository
    {
        protected override IQueryable<Department> LoadQuery<TQ>(TQ query)
        {
            IQueryable<Department> q = base.LoadQuery(query);
            var departmentQuery = query as DepartmentQuery;
            if (departmentQuery == null) return q;

            if (departmentQuery.ParentId.HasValue)
            {
                q = q.Where(c => c.Parent.Id == departmentQuery.ParentId);
            }

            if (!string.IsNullOrEmpty(departmentQuery.Name))
            {
                q = q.Where(c => c.Name.Contains(departmentQuery.Name));
            }

            if (departmentQuery.Level.HasValue)
            {
                q = q.Where(c => c.Level == departmentQuery.Level);
            }

            if (!string.IsNullOrEmpty(departmentQuery.Note))
            {
                q = q.Where(c => c.Note.Contains(departmentQuery.Note));
            }

            return q;
        }

        public override Department SaveOrUpdate(Department entity)
        {
            if (entity == null)
                return null;

            if (entity.Parent == null)
            {
                entity.Level = 0;
            }
            else
            {
               entity.Level = entity.Parent.Level + 1;
            }

            return base.SaveOrUpdate(entity);
        }

        public IList<Department> GetRoot()
        {
            return Query.Where(c => c.Level == 0).ToList();
        }

        public IList<Department> GetChildren(int parentId)
        {
            if (parentId < 1)
            {
                return GetRoot();
            }

            return Query.Where(c => c.Parent.Id == parentId).ToList();
        }
    }
}
