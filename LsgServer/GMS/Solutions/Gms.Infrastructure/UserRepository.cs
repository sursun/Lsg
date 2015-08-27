using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Gms.Domain;

namespace Gms.Infrastructure
{
    public class UserRepository : RepositoryBase<User>, IUserRepository
    {
        public User Get(string loginName)
        {
            return Query.FirstOrDefault(c => c.LoginName.Equals(loginName));
        }

        public User Get(Guid membershipId)
        {
            return Query.FirstOrDefault(c => c.MemberShipId.Equals(membershipId));
        }
    }
}
