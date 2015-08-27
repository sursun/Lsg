using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Gms.Domain;

namespace Gms.Infrastructure
{
    public class ManagerRepository : RepositoryBase<Manager>, IManagerRepository
    {
        public Manager Get(string loginName)
        {
            return Query.FirstOrDefault(c => c.LoginName.Equals(loginName));
        }

        public Manager Get(Guid membershipId)
        {
            return Query.FirstOrDefault(c => c.MemberShipId.Equals(membershipId));
        }
    }
}
