(function(){
  angular.module('primeiraApp').factory('msgs', [
    'toastr',
    MsgsFactory
  ])

  function MsgsFactory(toastr) {

    function addMsg(msgs, method) {
      if(msgs instanceof Array) {
        msgs.forEach(msg => toastr[method](msg))
      } else {
        toastr[method](msgs)
      }
    }

    function addSuccess(msgs) {
      addMsg(msgs,'success')
    }

    function addError(msgs) {
      addMsg(msgs, 'error')
    }
    
    function addWarning(msgs) {
        addMsg(msgs, 'warning')
    }

    return { addSuccess, addError, addWarning }
  }
})()
