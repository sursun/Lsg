using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Gms.Domain;
using Gms.Infrastructure;
using SharpArch.NHibernate.Contracts.Repositories;
using SharpArch.NHibernate.Web.Mvc;

namespace Gms.Web.Mvc.Controllers
{
    [HandleError]
    [Authorize]
    public class DepartmentController : BaseController
    {
        public ActionResult Index()
        {
            return View();
        }

        public ActionResult Select()
        {
            return View();
        }

        public ActionResult List()
        {
            var list = this.DepartmentRepository.GetRoot();
            var data = list.Select(c => DepartmentModel.From(c)).ToList();
            foreach (var department in data)
            {
                FillChildren(department);
            }

            //
            //var result = new { total = data.Count(), rows = data };
            return Json(data);
        }

        public void FillChildren(DepartmentModel departModel)
        {
            var list = this.DepartmentRepository.GetChildren(departModel.Id);
            var data = list.Select(c => DepartmentModel.From(c)).ToList();
            departModel.children = data;
            foreach (var department in data)
            {
                FillChildren(department);
            }
        }

        [Transaction]
        public ActionResult CreateOrUpdate(Department department)
        {
            if (department.Id > 0)
            {
                department = this.DepartmentRepository.Get(department.Id);

                TryUpdateModel(department);
            }

            this.DepartmentRepository.SaveOrUpdate(department);

            return JsonSuccess(department);
        }

        [Transaction]
        public ActionResult Delete(int id)
        {
            var item = DepartmentRepository.Get(id);
            if (item != null)
            {
                DepartmentRepository.Delete(item);
            }
            
            return JsonSuccess();
        }

        public ActionResult Get(int id)
        {
            var dept = this.DepartmentRepository.Get(id);
            return JsonSuccess(dept == null ? (new Department()) : dept);
        }
    }

    public class DepartmentModel
    {
        public int Id { get; set; }

        /// <summary>
        /// 上级科室
        /// </summary>
        public String Parent { get; set; }

        /// <summary>
        /// 科室名称
        /// </summary>
        public String Name { get; set; }

        /// <summary>
        /// 备注
        /// </summary>
        public String Note { get; set; }

        public IList<DepartmentModel> children { get; set; }

        public DepartmentModel(Department department)
        {
            this.Id = department.Id;

            this.Parent = department.ParentString();

            this.Name = department.Name;

            this.Note = department.Note;
        }

        public static DepartmentModel From(Department department)
        {
            return new DepartmentModel(department);
        }
    }
}