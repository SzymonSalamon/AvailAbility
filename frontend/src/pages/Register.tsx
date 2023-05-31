import { useNavigate } from "react-router-dom";
import RegisterForm from "../components/Login/RegisterForm";
import { useEffect } from "react";
import { getToken } from "../utils/auth";


const Register = () => {
  const navigate = useNavigate();

  useEffect(() => {
    const token = getToken();
    if (token) {
      navigate("/", { replace: true });
    }
  }, []);

  const onSuccessHandler = () => {
    navigate("/");
  };

  return (
    <div className="flex items-start justify-center sm:justify-start dark:bg-gray-900 dark:border-gray-700">

      <div className=" z-10  h-screen bg-zinc-200 sm:shadow-lg">
        <div
          className="g-6 flex h-full text-neutral-800 flex-col justify-center" >
          <div className="w-full">
            <div
              className="block rounded-lg bg-zinc-200">
              <div className="g-0 lg:flex lg:flex-wrap">

                <div className="px-4 md:px-0 lg:w-100%">
                  <div className="md:mx-6 md:p-12">
                    <div className="text-center">
                      <h4 className=" mt-1 pb-2 text-xl font-semibold">
                        Register
                      </h4>
                    </div>
                    <RegisterForm onSuccess={onSuccessHandler} />

                  </div>
                </div>

              </div>

            </div>
          </div>
        </div>
      </div>
      <div className="flex justify-center justify-items-center h-screen w-screen">
      <img
                        className="mx-auto w-auto p-40"
                        src="/public/backgorund_banner.png"
                        alt="logo" />
      </div>
      <div className="hidden sm:block">
 
      </div>
    </div>
  );
};

export default Register;
