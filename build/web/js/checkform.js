//Get button
let button = document.getElementById('button')

//Get ID data
let iscpName = document.getElementById('iscpName')
let isctName = document.getElementById('isctName')
let iscontactTitle = document.getElementById('iscontactTitle')
let isaddress = document.getElementById('isaddress')
let iscity = document.getElementById('iscity')
let isregion = document.getElementById('isregion')
let ispscode = document.getElementById('ispscode')
let iscountry = document.getElementById('iscountry')
let isphone = document.getElementById('isphone')
let isfax = document.getElementById('isfax')
let isuser = document.getElementById('isuser')
let ispass = document.getElementById('ispass')

//Get Data Input
let CompanyName = document.getElementById('CompanyName')
let ContactName = document.getElementById('ContactName')
let ContactTitle = document.getElementById('ContactTitle')
let Address = document.getElementById('Address')
let City = document.getElementById('City')
let Region = document.getElementById('Region')
let PostalCode = document.getElementById('PostalCode')
let Country = document.getElementById('Country')
let Phone = document.getElementById('Phone')
let Fax = document.getElementById('Fax')
let UserName = document.getElementById('UserName')
let PassWord = document.getElementById('PassWord')

//Style boder
let borderSucess = '2px solid green'
let borderFail = '2px solid red'

button.addEventListener('click', () => {
  //Check CompanyName
  if (CompanyName.value == '') {
    CompanyName.style.border = borderFail
    iscpName.innerHTML = 'Not Empry'
    iscpName.style.display = 'block'
  } else {
    if (CompanyName.value.length > 40) {
      CompanyName.style.border = borderFail
      iscpName.style.display = 'block'

    } else {
      CompanyName.style.border = borderSucess
      iscpName.style.display = 'none'
    }
  }
  //Check ContactName
  if (ContactName.value == '') {
    ContactName.style.border = borderFail
    isctName.innerHTML = 'Not Empry'
    isctName.style.display = 'block'
  } else {
    if (ContactName.value.length > 30) {
      ContactName.style.border = borderFail
      isctName.style.display = 'block'

    } else {
      ContactName.style.border = borderSucess
      isctName.style.display = 'none'
    }
  }
  //Check ContactTitle
  if (ContactTitle.value.length > 30) {
    ContactTitle.style.border = borderFail
    iscontactTitle.style.display = 'block'

  } else {
    ContactTitle.style.border = borderSucess
    iscontactTitle.style.display = 'none'
  }
  //Check Address
  if (Address.value.length > 60) {
    Address.style.border = borderFail
    isaddress.style.display = 'block'

  } else {
    Address.style.border = borderSucess
    isaddress.style.display = 'none'
  }
  //Check City
  if (City.value.length > 15) {
    City.style.border = borderFail
    iscity.style.display = 'block'

  } else {
    City.style.border = borderSucess
    iscity.style.display = 'none'
  }
  //Check Region
  if (Region.value.length > 15) {
    Region.style.border = borderFail
    isregion.style.display = 'block'

  } else {
    Region.style.border = borderSucess
    isregion.style.display = 'none'
  }
  //Check PostalCode
  if (PostalCode.value.length > 10) {
    PostalCode.style.border = borderFail
    ispscode.style.display = 'block'

  } else {
    PostalCode.style.border = borderSucess
    ispscode.style.display = 'none'
  }
  //Check Country
  if (Country.value.length > 15) {
    Country.style.border = borderFail
    iscountry.style.display = 'block'

  } else {
    Country.style.border = borderSucess
    iscountry.style.display = 'none'
  }
  //Check Phone
  if (Phone.value.length > 24) {
    Phone.style.border = borderFail
    isphone.style.display = 'block'

  } else {
    Phone.style.border = borderSucess
    isphone.style.display = 'none'
  }
  //Check Fax
  if (Fax.value.length > 24) {
    Fax.style.border = borderFail
    isfax.style.display = 'block'

  } else {
    Fax.style.border = borderSucess
    isfax.style.display = 'none'
  }
  //Check User
  if (UserName.value == '') {
    UserName.style.border = borderFail
    isuser.style.display = 'block'
  } else {
    UserName.style.border = borderSucess
    isuser.style.display = 'none'
  }
  //Check Pass
  if (PassWord.value == '') {
    PassWord.style.border = borderFail
    ispass.style.display = 'block'
  } else {
    PassWord.style.border = borderSucess
    ispass.style.display = 'none'
  }
})

button.addEventListener('click', (e) => {
  e.preventDefault()
})



