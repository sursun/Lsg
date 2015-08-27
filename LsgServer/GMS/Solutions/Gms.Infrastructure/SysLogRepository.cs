using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Gms.Domain;

namespace Gms.Infrastructure
{
    public class SysLogRepository : RepositoryBase<SysLog>, ISysLogRepository
    {
        protected override IQueryable<SysLog> LoadQuery<TQ>(TQ query)
        {
            IQueryable<SysLog> q = base.LoadQuery(query);
            var sysLogQuery = query as SysLogQuery;
            if (sysLogQuery == null) return q;

            if (!string.IsNullOrEmpty(sysLogQuery.LogInfo))
            {
                q = q.Where(c => c.LogInfo.Contains(sysLogQuery.LogInfo));
            }

            if (!string.IsNullOrEmpty(sysLogQuery.LoginName))
            {
                q = q.Where(c => c.Manager.LoginName.Contains(sysLogQuery.LoginName));
            }

            if (sysLogQuery.CreateTime != null)
            {
                if (sysLogQuery.CreateTime.Start.HasValue)
                {
                    q = q.Where(c => c.CreateTime >= sysLogQuery.CreateTime.Start);
                }

                if (sysLogQuery.CreateTime.End.HasValue)
                {
                    q = q.Where(c => c.CreateTime < sysLogQuery.CreateTime.End);
                }
            }

            return q;
        }

        public SysLog Write(string log, Manager manager)
        {
            return base.SaveOrUpdate(new SysLog() { LogInfo = log, Manager = manager, CreateTime = DateTime.Now });
        }

    }
}
