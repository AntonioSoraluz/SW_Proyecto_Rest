var tipo = document.getElementById('RolOption');
tipo.checked = true;
document.getElementById('textoRuc').style.display='none';
document.getElementById('ruc').required = false;
setTimeout(function(){
	document.getElementById('alerta').remove();
}, 5000);
function hideShowDiv(val){
	if(val==1){
		document.getElementById('textoRuc').style.display='none';
		document.getElementById('ruc').required = false;
	}
	if(val==2){
		document.getElementById('textoRuc').style.display='block';
		document.getElementById('ruc').required = true;
	}
}

(() => {
  'use strict'

  const forms = document.querySelectorAll('.needs-validation')

  Array.from(forms).forEach(form => {
    form.addEventListener('submit', event => {
      if (!form.checkValidity()) {
        event.preventDefault()
        event.stopPropagation()
      }

      form.classList.add('was-validated')
    }, false)
  })
})()