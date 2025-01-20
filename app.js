// El principal objetivo de este desafío es fortalecer tus habilidades en lógica de programación. Aquí deberás desarrollar la lógica para resolver el problema.

let amigos = [];

function agregarAmigo(){
  let amigo = document.querySelector('#amigo').value;
  
  if(amigo){
    amigos.push(amigo);
    console.log(amigos);
  }else{
    alert('Por favor, inserte un nombre');
  }
  document.querySelector('#amigo').value = '';
  actualizarListado();
}


function actualizarListado(){
  let lista = document.querySelector('#listaAmigos');
  lista.innerHTML = '';
  
  amigos.forEach( amigo=>{
    let li = document.createElement('li');
    li.innerText = amigo;
    lista.appendChild(li);
  });
}

function sortearAmigo(){
  let lista = document.querySelector('#listaAmigos');

  if(amigos.length){
    let random = Math.floor(Math.random()*amigos.length)+1;

  }else{
    lista.innerText = 'No hay amigos que sortear :(';
  }
}