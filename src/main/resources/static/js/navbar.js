const navbarLinks = document.getElementsByClassName('app__navbar-links');
const navbarDesktopLinks = document.getElementsByClassName('app__navbar-desktop-links');

const menuButton = document.getElementById('app__navbar-menu-btn');
const mobileMenu = document.getElementById('app__navbar-menu');


if (isAuthenticated) {
    navbarLinks[0].innerHTML = links.map(link =>
        `<li class="app__flex p-text">
            <a href="/${link.split(' ').join('')}" class="app__navbar-link">${link}</a>
        </li>`
    ).join('');
} else {
    navbarLinks[0].innerHTML = '';
}


// Arrow function to show/hide menu
const toggleMenu = (toggle) => {
    console.log(toggle);
    if (toggle) {
        navbarLinks[0].innerHTML = links.map(link =>`
            <li class="app__flex p-text">
                <a href="/${link.split(' ').join('')}" class="app__navbar-link">${link}</a>
            </li>`
        ).join('');
        mobileMenu.innerHTML = `
            <div id="app__navbar-menu-btn" class="app__nabvar-mobile">
                <i class="material-symbols-outlined" onclick="toggleMenu(false)">
                    close
                </i>
                
                <ul class="app__navbar-links">
                    ${navbarLinks[0].innerHTML}
                </ul>

            </div>
        `;
    } else if (!toggle) {
        mobileMenu.innerHTML = ``;
    }
}

// Event listener to show/hide menu
menuButton.addEventListener('click', toggleMenu);