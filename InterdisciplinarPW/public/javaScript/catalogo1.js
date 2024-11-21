// Função para abrir o modal de confirmação
function openModal(nomeFilme) {
  // Exibe o modal
  document.getElementById("modalConfirmar").style.display = "flex";
  
  // Adiciona um listener para o botão "Sim"
  document.getElementById("btnConfirmar").onclick = function() {
      confirmarExclusao(nomeFilme);  // Chama a função para excluir o filme
  };
  
  // Adiciona um listener para o botão "Cancelar"
  document.getElementById("btnCancelar").onclick = function() {
      fecharModal();  // Apenas fecha o modal
  };
}

// Função para fechar o modal
function fecharModal() {
  document.getElementById("modalConfirmar").style.display = "none";
}

// Função para excluir o filme
function confirmarExclusao(nomeFilme) {
  // Realiza a requisição para o PHP excluir o filme
  var xhr = new XMLHttpRequest();
  xhr.open("POST", "excluirFilme.php", true);
  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  xhr.onreadystatechange = function() {
      if (xhr.readyState == 4 && xhr.status == 200) {
          alert("Filme excluído com sucesso!");
          window.location.reload();  // Atualiza a página para refletir a exclusão
      }
  };
  xhr.send("nome=" + encodeURIComponent(nomeFilme));
}
