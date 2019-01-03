using System;
using System.Collections.Generic;

namespace Webapi.Models
{
    public partial class Flour
    {
        public int Flourid { get; set; }
        public string Flour1 { get; set; }
        public int UserUserid { get; set; }

        public User UserUser { get; set; }
    }
}
