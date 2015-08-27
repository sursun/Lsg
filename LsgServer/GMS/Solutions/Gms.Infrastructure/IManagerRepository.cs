using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Gms.Domain;

namespace Gms.Infrastructure
{
    public interface IManagerRepository : IRepositoryBase<Manager>
    {
        Manager Get(string loginName);
        Manager Get(Guid membershipId);
    }
}
