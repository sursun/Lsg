using System;
using System.Collections.Generic;
using System.Data;
using System.Data.OleDb;
using System.Linq;
using System.Text;

namespace Gms.Common
{
    public static class DataTableExtensions
    {
        public static DataTable GetOledbFirstTableData(this string dbFile)
        {
            string strConn = "Provider=Microsoft.Jet.OleDb.4.0; Data Source=" + dbFile + "; Extended Properties=Excel 8.0;";

            using (var conn = new OleDbConnection(strConn))
            {
                conn.Open();

                var schema = conn.GetSchema("Tables");
                string strSql = string.Format("select * from [{0}]", schema.Rows[0]["TABLE_NAME"]);

                DataTable dt = null;

                if (schema.Rows.Count > 0)
                {
                    var ds = new DataSet();
                    var da = new OleDbDataAdapter(strSql, conn);
                    da.Fill(ds); // 填充DataSet   
                    dt = ds.Tables[0];

                }
                return dt;
            }
        }
    }
}
