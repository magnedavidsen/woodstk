VAGRANTFILE_API_VERSION = "2"

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|
  config.vm.box = "precise64"
  config.vm.network :private_network, ip: "192.168.111.222"
  config.vm.box_url = "http://files.vagrantup.com/precise64.box"
  config.vm.synced_folder "src/", "/src"

  config.vm.provision "ansible" do |ansible|
  	ansible.verbose = true
    ansible.playbook = "buildvm.yml"
  end
end
