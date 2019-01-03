using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Mill.Models;

namespace Mill.Controllers
{
    public class HomeController : Controller
    {
        public IActionResult Index()
        {
            return View();
        }


        public IActionResult lndex()
        {
            ViewData["Message"] = "Index page";

            return View();
        }

        public IActionResult Generate()
        {
            ViewData["Message"] = "Generate flour";

            return View();
        }

        [HttpGet]
        public IActionResult Exchange()
        {
            ViewData["Message"] = "Exchange flour";

            return View();
        }

        public IActionResult Privacy()
        {
            return View();
        }


        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}
