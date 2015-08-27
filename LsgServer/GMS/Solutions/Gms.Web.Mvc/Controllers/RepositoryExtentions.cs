using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Web;
using System.Web.Mvc;
using System.Web.Mvc.Html;
using Gms.Domain;


namespace Gms.Web.Mvc.Controllers
{
    public static class RepositoryExtentions
    {
        public static MvcHtmlString DropDownListForCommonCode<T>(this HtmlHelper<T> helper, Expression<Func<T, CommonCode>> commonCodeExpression, CommonCodeType type)
        {
            return helper.DropDownListForCommonCode(commonCodeExpression, type, null);
        }

        public static MvcHtmlString DropDownListForCommonCode<T>(this HtmlHelper<T> helper, Expression<Func<T, CommonCode>> commonCodeExpression, CommonCodeType type,string optionLabel)
        {
            var val = ModelMetadata.FromLambdaExpression(commonCodeExpression, helper.ViewData);
            var selectval = val.Model;

            var list = helper.GetCommonCodeList(type, selectval);
            return helper.SelectInternal(optionLabel, ExpressionHelper.GetExpressionText(commonCodeExpression), list, false, null);
            
        }

        public static MvcHtmlString DropDownListForCommonCode<T>(this HtmlHelper<T> helper, Expression<Func<T, CommonCode>> commonCodeExpression, CommonCodeType type,string name, string optionLabel)
        {
            var list = helper.GetCommonCodeList(type, null);
            return helper.SelectInternal(optionLabel, name, list, false, null);

        }

        public static IEnumerable<SelectListItem> From<TProperty, TIdProperty, TTextProperty>(this IEnumerable<TProperty> items, Expression<Func<TProperty, TIdProperty>> idExpression, Expression<Func<TProperty, TTextProperty>> textExpression)
        {
            var idFunc = idExpression.Compile();
            var textFunc = textExpression.Compile();
            var list = items.Select(c => new SelectListItem { Value = idFunc(c).ToString(), Text = textFunc(c).ToString() });
            return list;
        }

        private static IEnumerable<SelectListItem> GetCommonCodeList<T>(this HtmlHelper<T> helper, CommonCodeType type , object selectval)
        {
            IEnumerable<SelectListItem> selectList = null;

            IList<CommonCode> list = new List<CommonCode>();
           
            var controller = helper.ViewContext.Controller as BaseController;
            if (controller!= null)
                list = controller.CommonCodeRepository.GetRoot(type);

            foreach (var commonCode in list)
            {
                bool bFlag = false;
                bFlag = commonCode.Equals(selectval);

                Console.WriteLine(bFlag);
            }

            selectList = from CommonCode item in list
                select new SelectListItem
                {
                    Value = item.Id.ToString(),
                    Text = item.Name,
                    Selected = (item.Equals(selectval))
                };

            return selectList;
        }

        internal static string ListItemToOption(SelectListItem item)
        {
            TagBuilder builder = new TagBuilder("option")
            {
                InnerHtml = HttpUtility.HtmlEncode(item.Text)
            };
            if (item.Value != null)
            {
                builder.Attributes["value"] = item.Value;
            }
            if (item.Selected)
            {
                builder.Attributes["selected"] = "selected";
            }
            return builder.ToString(TagRenderMode.Normal);
        }

        private static MvcHtmlString SelectInternal(this HtmlHelper htmlHelper, string optionLabel, string name, IEnumerable<SelectListItem> selectList, bool allowMultiple, IDictionary<string, object> htmlAttributes)
        {
            ModelState state;
            name = htmlHelper.ViewContext.ViewData.TemplateInfo.GetFullHtmlFieldName(name);
            if (string.IsNullOrEmpty(name))
            {
                throw new ArgumentException("name is Null", "name");
            }

            StringBuilder builder = new StringBuilder();
            if (optionLabel != null)
            {
                builder.AppendLine(ListItemToOption(new SelectListItem { Text = optionLabel, Value = string.Empty, Selected = false }));
            }
            foreach (SelectListItem item3 in selectList)
            {
                builder.AppendLine(ListItemToOption(item3));
            }
            TagBuilder builder2 = new TagBuilder("select")
            {
                InnerHtml = builder.ToString()
            };
            builder2.MergeAttributes<string, object>(htmlAttributes);
            builder2.MergeAttribute("name", name, true);
            builder2.GenerateId(name);
            if (allowMultiple)
            {
                builder2.MergeAttribute("multiple", "multiple");
            }
            if (htmlHelper.ViewData.ModelState.TryGetValue(name, out state) && (state.Errors.Count > 0))
            {
                builder2.AddCssClass(HtmlHelper.ValidationInputCssClassName);
            }

            return MvcHtmlString.Create(builder2.ToString(TagRenderMode.Normal));

        }

    }
}