using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Gms.Domain;
using SharpArch.Domain.PersistenceSupport;

namespace Gms.Infrastructure
{
    public interface ISysLogRepository : IRepositoryBase<SysLog>
    {
        SysLog Write(string log, Manager manager);
    }
}
