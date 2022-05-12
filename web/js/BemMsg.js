//Show Toast Function
function toast({ title ='', message ='', type = "warning", duration = 3000 }) {
  const main = document.getElementById("toast");
  //Check nếu có main thì tạo ra toast và đọc thông tin từ object vào
  if(main){
      //Tạo div nếu có main
      const toast = document.createElement('div');
      //Auto remove toast duration + thời gian vào
      const autoRemoveId = setTimeout(function() {
        main.removeChild(toast);
      }, duration + 1000);

      //Autoremove toast when clicks
      toast.onclick = function(e){
          if(e.target.closest('.toast__close')){
            main.removeChild(toast);
            clearTimeout(autoRemoveId);
          }
      }

      // Tạo biến lưu trữ icon
      const icons = {
          success: "fas fa-check-circle",
          info: "fas fa-exclamation-circle",
          error: "fas fa-exclamation-circle",
      };
      const icon = icons[type];//Lấy ra icon tương đương type
      const delay = (duration/1000).toFixed(2);//Tính time delay
      //add class toast vào và thêm type modifier
      toast.classList.add('toast', `toast--${type}`);
      //Tạo amination
      toast.style.animation = `slideInLeft ease 0.5s, fadeOut linear 1s ${delay}s forwards`;

      toast.innerHTML = `
            <div class="toast__icon">
              <i class="${icon}"></i>
            </div>
            <div class="toast__body">
              <h2 class="toast__title">${title}</h2>
              <p class="toast_msg">
                ${message}
              </p>
            </div>
            <div class="toast__close">
              <i class="fas fa-times"></i>
      `;
      // Sau khi tạo xong thì thêm vào main
      main.appendChild(toast);
      
  }
}

// Tạo object để lưu giá trị
// toast({
//   title: "Success 123",
//   message: "Máy tính111",
//   type: "error",
//   duration: 3000,
// });

//Truyền giá trị vào cho mỗi click phù hợp
function showSuccessToast(){
  toast({
        title: "Đăng Kí Thành Công!",
        message: "Chúc Mừng bạn đã đăng kí tài khoản thành công mời bạn trải nghiệm.",
        type: "success",
        duration: 5000,
  });
}
function showInfoToast(){
  toast({
        title: "Check Info!",
        message: "Thử lại xem có lỗi gì đi.",
        type: "info",
        duration: 5000,
  });
}
function showErrorToast(){
  toast({
        title: "Đăng Kí Thất Bại!",
        message: "Bạn bị ngu rồi đăng kí lại đi haha.",
        type: "error",
        duration: 5000,
  });
}