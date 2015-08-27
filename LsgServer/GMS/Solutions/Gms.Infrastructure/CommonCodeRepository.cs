using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Gms.Common;
using Gms.Domain;

namespace Gms.Infrastructure
{
    public class CommonCodeRepository : RepositoryBase<CommonCode>, ICommonCodeRepository
    {
        protected override IQueryable<CommonCode> LoadQuery<TQ>(TQ query)
        {
            IQueryable<CommonCode> q = base.LoadQuery(query);
            var commonCodeQuery = query as CommonCodeQuery;
            if (commonCodeQuery == null) return q;

            if (commonCodeQuery.ParentId.HasValue)
            {
                q = q.Where(c => c.Parent.Id == commonCodeQuery.ParentId);
            }

            if (!string.IsNullOrEmpty(commonCodeQuery.Name))
            {
                q = q.Where(c => c.Name.Contains(commonCodeQuery.Name));
            }

            if (commonCodeQuery.Type.HasValue)
            {
                q = q.Where(c => c.Type == commonCodeQuery.Type);
            }

            return q;
        }

        public IList<CommonCode> GetRoot(CommonCodeType type)
        {
            return Query.Where(c => (c.Parent == null && c.Type == type) ).ToList();
        }

        public IList<CommonCode> GetChildren(int parentId)
        {
            return Query.Where(c => c.Parent.Id == parentId).ToList();
        }
        

        public override CommonCode SaveOrUpdate(CommonCode entity)
        {
            if (entity.Id < 1)
            {
                var commonCode = GetBy(entity.Name, entity.Type);
                if (commonCode != null)
                {
                    throw new Exception("该编码已经存在");
                }
            }
            //this.Session.Evict(glassType);
            return base.SaveOrUpdate(entity);
        }

        public CommonCode GetBy(string name, CommonCodeType type)
        {
            return Query.FirstOrDefault(c => (c.Type == type && c.Name.Equals(name)));
        }

        public CommonCode GetBy(CommonCodeType type)
        {
            return Query.FirstOrDefault(c => (c.Type == type ));
        }

        public CommonCode GetOrCreate(string name, CommonCodeType type)
        {
            if(name.IsNullOrEmpty())
                return null;

            var commonCode = GetBy(name, type);
            if (commonCode == null)
            {
                return base.SaveOrUpdate(new CommonCode(){Name = name,Type = type});
            }
            //this.Session.Evict(glassType);
            return commonCode;
        }
    }
}
