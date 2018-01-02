const util = require('util')
var VM = require('ethereumjs-vm')


//create a new VM instance
var vm = new VM()
var code = '6060604052600436106100405763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663ea8796348114610154575b662386f26fc100003410610152577fec29ee18c83562d4f2e0ce62e38829741c2901da844c015385a94d8c9f03d486600260003660116000604051602001526040517f485631372d00000000000000000000000000000000000000000000000000000081526005810184848082843782019150508260ff167f0100000000000000000000000000000000000000000000000000000000000000028152600101935050505060206040518083038160008661646e5a03f1151561010157600080fd5b5050604051805190506040519081526040602082018190526011818301527f596f7572206b657920697320686572652e00000000000000000000000000000060608301526080909101905180910390a15b005b341561015f57600080fd5b61015260005473ffffffffffffffffffffffffffffffffffffffff9081169030163180156108fc0290604051600060405180830381858888f1935050505015156101a857600080fd5b5600a165627a7a7230582020304ba8cb5786445e5c47f840741111591a38057d40ac139568b31f9eaee3c70029'

vm.on('step', function (data) {
  console.log(data.opcode.name)
})

vm.runCode({
  code: Buffer.from(code, 'hex'), // code needs to be a Buffer
  gasLimit: Buffer.from('ffffffff', 'hex'),
  data: Buffer.from( '616e67656c30666461726b6e657373' /*'5468756d706572'*/, 'hex'),
  value: '0x2386f26fc10000'
}, function(err, results) {
  if (err) {
    console.log('err: ' + err);
  } else {
    //console.log('returned: ' + results.result.toString('hex'));
    console.log(util.inspect(results, {depth: 1}));
  }
})
