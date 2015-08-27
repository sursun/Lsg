using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace TestPropertyInfo
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Doctor doctor = new Doctor();
            doctor.Id = 1;
            doctor.CodeoNo = "sdf";
            doctor.CreateDateTime = DateTime.Now;

            doctor.Computer();
            MessageBox.Show(string.Format("Total[{0}],Completed[{1}]", doctor.Total, doctor.Completed));
        }
    }
}
