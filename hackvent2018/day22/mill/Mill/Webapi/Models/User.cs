using System;
using System.Collections.Generic;

namespace Webapi.Models
{
    public partial class User
    {
        public User()
        {
            Flour = new HashSet<Flour>();
        }

        public int Userid { get; set; }
        public string Username { get; set; }
        public string Password { get; set; }

        public ICollection<Flour> Flour { get; set; }
    }
}
