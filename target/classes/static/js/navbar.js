const navbarLinks = document.getElementsByClassName('app__navbar-links');
const navbarDesktopLinks = document.getElementById('app__navbar-desktop-links');

const menuButton = document.getElementById('app__navbar-menu-btn');
const mobileMenu = document.getElementById('app__navbar-menu');


if (isAuthenticated) {
    navbarDesktopLinks.innerHTML = `
        <li class="app__flex p-text">
            <a href="/secure" th:href="@{/secure}" class="app__navbar-link">
                Home
            </a>
        </li>
        <li class="app__flex p-text">
            <a href="/secure/check" th:href="@{/secure/check}" class="app__navbar-link">
                Check
            </a>
        </li>
    `;
} else {
    navbarDesktopLinks.innerHTML = '';
}


// Arrow function to show/hide menu
const toggleMenu = (toggle) => {
    console.log(toggle);
    if (isAuthenticated) {
        if (toggle) {
            mobileMenu.innerHTML = `
            <div id="app__navbar-menu-btn" class="app__nabvar-mobile">
                <i class="material-symbols-outlined" onclick="toggleMenu(false)">
                    close
                </i>
                
                <ul class="app__navbar-links">
                    <li class="app__navbar-item">
                        <a href="/secure" th:href="@{/secure}" class="app__navbar-link">
                            <i class="material-symbols-outlined">
                                home
                            </i>
                            Home
                        </a>
                    </li>
                    <li class="app__navbar-item">
                        <a href="/secure/check" th:href="@{/secure/check}" class="app__navbar-link">
                            <i class="material-symbols-outlined">
                                check
                            </i>
                            Check
                        </a>
                    </li>
                    <li class="app__navbar-item">
                        <a href="/logout" th:href="@{/logout}" class="app__navbar-link">
                            <i class="material-symbols-outlined">
                                logout
                            </i>
                            Log Out
                        </a>
                    </li>
                </ul>
            </div>
        `;
        } else if (!toggle) {
            mobileMenu.innerHTML = ``;
        }
    } else {
        if (toggle) {
            if (pageType === 'login') {
                mobileMenu.innerHTML = `
                    <div id="app__navbar-menu-btn" class="app__nabvar-mobile">
                        <i class="material-symbols-outlined" onclick="toggleMenu(false)">
                            close
                        </i>
                        
                        <ul class="app__navbar-links">
                            <li class="app__navbar-item">
                                <a href="/signup" th:href="@{/sigup}" class="app__navbar-link">
                                    <i class="material-symbols-outlined">
                                        person_add
                                    </i>
                                    Register
                                </a>
                            </li>
                        </ul>
                    </div>
                `;
            } else if (pageType === 'signup') {
                mobileMenu.innerHTML = `
                    <div id="app__navbar-menu-btn" class="app__nabvar-mobile">
                        <i class="material-symbols-outlined" onclick="toggleMenu(false)">
                            close
                        </i>

                        <ul class="app__navbar-links">
                            <li class="app__navbar-item">
                                <a href="/login" th:href="@{/login}" class="app__navbar-link">
                                    <i class="material-symbols-outlined">
                                        login
                                    </i>
                                    Login
                                </a>
                            </li>
                        </ul>
                    </div>
                `;
            } else {
                mobileMenu.innerHTML = `
                    <div id="app__navbar-menu-btn" class="app__nabvar-mobile">
                        <i class="material-symbols-outlined" onclick="toggleMenu(false)">
                            close
                        </i>
                        
                        <ul class="app__navbar-links">
                            <li class="app__navbar-item">
                                <a href="/login" th:href="@{/login}" class="app__navbar-link">
                                    <i class="material-symbols-outlined">
                                        login
                                    </i>
                                    Log In
                                </a>
                            </li>
                            <li class="app__navbar-item">
                                <a href="/signup" th:href="@{/sigup}" class="app__navbar-link">
                                    <i class="material-symbols-outlined">
                                        person_add
                                    </i>
                                    Register
                                </a>
                            </li>
                        </ul>
                    </div>
            `;
            }
        } else if (!toggle) {
            mobileMenu.innerHTML = ``;
        }
    }
}

// Event listener to show/hide menu
menuButton.addEventListener('click', toggleMenu);