using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Gms.Domain;

namespace Gms.Infrastructure
{
    public class UserEvalRepository : RepositoryBase<UserEval>, IUserEvalRepository
    {
        public IList<UserEval> GetAll(string username)
        {
            return Query.Where(c => c.ToUser.LoginName.Equals(username)).ToList();
        }
    }
}
