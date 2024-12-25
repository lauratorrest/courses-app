using accounts.Models;
using accounts.Services;
using Microsoft.AspNetCore.Http.HttpResults;
using Microsoft.AspNetCore.Mvc;

namespace accounts.Controllers
{

    [ApiController]
    [Route("/accounts")]
    public class AccountController : ControllerBase
    {

        private readonly AccountGetService _accountGetService;
        private readonly AccountSaveService _accountSaveService;

        public AccountController(
            AccountGetService accountGetService,
            AccountSaveService accountSaveService)
        {
            _accountGetService = accountGetService;
            _accountSaveService = accountSaveService;
        }

        [HttpGet("GetAll")]
        public async Task<List<Account>> Get() =>
            await _accountGetService.Get();

        [HttpGet("GetById/{id:length(24)}")]
        public async Task<ActionResult<Account>> Get(string id)
        {
            Account account = await _accountGetService.Get(id);

            if(account is not null)
            {
                return account;
            }

            return null;
        }

        [HttpPost("Save")]
        public async Task<IActionResult> Post(Account account)
        {
            await _accountSaveService.SaveAccount(account);
            return CreatedAtAction(nameof(Get), new { id = account.Id }, account);
        }

    }
}
