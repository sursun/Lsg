using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Gms.Domain;

namespace Gms.Infrastructure
{
    public class TaskAskRepository : RepositoryBase<TaskAsk>, ITaskAskRepository
    {
        public IList<TaskAsk> GetAll(int taskid)
        {
            return Query.Where(c => c.Task.Id == taskid).ToList();
        }
    }
}
