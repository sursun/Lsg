using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Gms.Domain;

namespace Gms.Infrastructure
{
    public class TaskApplyRepository : RepositoryBase<TaskApply>, ITaskApplyRepository
    {
        public IList<TaskApply> GetAll(int taskid)
        {
            return Query.Where(c => c.Task.Id == taskid).ToList();
        }
    }
}
