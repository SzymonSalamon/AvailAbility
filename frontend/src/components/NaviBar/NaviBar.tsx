import React, { FC, useEffect, useState, useRef } from 'react';
import fetchApi from '../../utils/fetchApi';

interface NaviBarProps {}

interface User {
  lastName: string;
  firstName: string;
  mail: string;
}

interface Employee {
  id: number;
  lastname: string;
  firstname: string;
  mail: string;
}

const NaviBar: FC<NaviBarProps> = () => {
  const [user, setUser] = useState<User | null>(null);
  const [employees, setEmployees] = useState<Employee[]>([]);
  const dropdownRef = useRef<HTMLDivElement | null>(null);
  const employeeDropdownRef = useRef<HTMLDivElement | null>(null);

  const toggleDropdown = (dropdownRef: React.RefObject<HTMLDivElement>) => {
    const dropdown = dropdownRef.current;
    if (!dropdown) return;

    dropdown.style.display = dropdown.style.display === 'none' ? 'block' : 'none';
  };

  useEffect(() => {
    const handleClickOutside = (event: MouseEvent) => {
      if (
        dropdownRef.current &&
        !dropdownRef.current.contains(event.target as Node) &&
        employeeDropdownRef.current &&
        !employeeDropdownRef.current.contains(event.target as Node)
      ) {
        if (dropdownRef.current.style.display !== 'none') {
          dropdownRef.current.style.display = 'none';
        }
        if (employeeDropdownRef.current.style.display !== 'none') {
          employeeDropdownRef.current.style.display = 'none';
        }
      }
    };

    const fetchUser = async () => {
      try {
        const response = await fetchApi('/getuser', {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
          },
        });
        const data = await response.json();
        setUser(data);
      } catch (error) {
        console.error('Error fetching User Data:', error);
      }
    };

    const fetchEmployees = async () => {
      try {
        const response = await fetchApi('/manageusers', {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
          },
        });
        const data = await response.json();
        setEmployees(data);
      } catch (error) {
        console.error('Error fetching User Data:', error);
      }
    };

    fetchUser();
    fetchEmployees();

    document.addEventListener('mousedown', handleClickOutside);

    return () => {
      document.removeEventListener('mousedown', handleClickOutside);
    };
  }, []);

   return (
    <nav className="bg-white border-gray-200 dark:bg-gray-900">
      <div className="max-w-screen-xl flex flex-wrap items-center justify-between p-4">
        <div className="flex items-start md:order-2">
          <button
            type="button"
            className="flex mr-3 text-sm bg-gray-800 rounded-full md:mr-0 focus:ring-4 focus:ring-gray-300 dark:focus:ring-gray-600"
            onClick={() => toggleDropdown(dropdownRef)}
          >
            <img
              className="w-8 h-8 rounded-full"
              src="/docs/images/people/profile-picture-3.jpg"
              alt=" "
            />
          </button>
          <div
            className="absolute z-50 hidden my-4 text-base list-none bg-white divide-y divide-gray-100 rounded-lg shadow dark:bg-gray-700 dark:divide-gray-600"
            id="user-dropdown"
            ref={dropdownRef}
            style={{ display: 'none' }}
          >
            <div className="px-4 py-3">
              {user && (
                <>
                  <span className="block text-sm text-gray-900 dark:text-white">
                    {user.firstName + ' ' + user.lastName}
                  </span>
                  <span className="block text-sm text-gray-500 truncate dark:text-gray-400">
                    {user.mail}
                  </span>
                </>
              )}
            </div>
            <ul className="py-2">
              {employees.length > 0 && (
                <li>
                  <a
                    className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white"
                    onClick={() => toggleDropdown(employeeDropdownRef)}
                  >
                    Employee
                  </a>
                </li>
              )}
              <div
                className={`absolute z-50 hidden my-4 text-base list-none bg-white divide-y divide-gray-100 rounded-lg shadow dark:bg-gray-700 dark:divide-gray-600 ${
                  employees.length === 0 ? 'hidden' : ''
                }`}
                id="employee-dropdown"
                ref={employeeDropdownRef}
                style={{ display: 'none' }}
              >
                <ul>
                  {employees.map((employee) => (
                    <li key={employee.id}>
                      <a
                        href={`/manage?username=${employee.mail}`}
                        className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white"
                      >
                        {employee.mail}
                      </a>
                    </li>
                  ))}
                </ul>
              </div>
              <li>
                <a
                  href="/"
                  className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white"
                >
                  My Calendar
                </a>
              </li>
              <li>
                <a
                  href="/logout"
                  className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white"
                >
                  Sign out
                </a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </nav>
  );
};

export default NaviBar;
