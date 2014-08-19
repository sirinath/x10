package apgas.impl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import com.hazelcast.config.Config;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import com.hazelcast.core.IList;
import com.hazelcast.core.ItemEvent;
import com.hazelcast.core.ItemListener;
import com.hazelcast.core.Member;
import com.hazelcast.core.MemberAttributeEvent;
import com.hazelcast.core.MembershipEvent;
import com.hazelcast.core.MembershipListener;
import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;

/**
 * The {@link HazelcastTransport} class implements the global runtime by means
 * of an Hazelcast cluster.
 * <p>
 * It implements active messages on top of a distributed executor service.
 */
final class HazelcastTransport implements ItemListener<Member>,
    MessageListener<Runnable>, MembershipListener {
  private final HazelcastInstance hazelcast;
  private final int here;
  private int places;
  private final IList<Member> members;
  private final String regMembershipListener;
  private final String regItemListener;
  private final IExecutorService executor;
  private final Runnable kill;

  // private final ITopic<VoidFun> topic;
  // private final String regTopic;

  /**
   * Initializes the {@link HazelcastInstance} for this global runtime instance.
   *
   * @param kill
   *          a function to invoke to shutdown the sheduler for this global
   *          runtime instance.
   * @param master
   *          required member to connect to or null
   * @throws IOException
   *           if localhost cannot be resolved
   */
  HazelcastTransport(Runnable kill, String master) throws IOException {
    this.kill = kill;

    final Config config = new Config();
    final NetworkConfig network = config.getNetworkConfig();
    network.getJoin().getMulticastConfig().setEnabled(false);
    network.getJoin().getTcpIpConfig().setEnabled(true);
    network.setInterfaces(network.getInterfaces()
        .addInterface(InetAddress.getLocalHost().getHostAddress())
        .setEnabled(true));
    if (master != null) {
      if (master.contains(":")) {
        network.getJoin().getTcpIpConfig().setRequiredMember(master);
      } else {
        network.getJoin().getTcpIpConfig().addMember(master);
      }
    }
    config.setProperty("hazelcast.logging.type", "none");
    config.setProperty("hazelcast.wait.seconds.before.join", "0");
    hazelcast = Hazelcast.newHazelcastInstance(config);
    executor = hazelcast.getExecutorService("APGAS");
    members = hazelcast.<Member> getList("APGAS");

    members.add(hazelcast.getCluster().getLocalMember());
    regItemListener = members.addItemListener(this, false);
    int here = 0;
    for (final Member m : members) {
      if (m.localMember()) {
        break;
      }
      here++;
    }
    this.here = here;
    places = members.size();

    // topic = hazelcast.getTopic("APGAS" + here);
    // regTopic = topic.addMessageListener(this);

    regMembershipListener = hazelcast.getCluster().addMembershipListener(this);
  }

  /**
   * Returns the socket address of this {@link Hazelcast} instance.
   *
   * @return an address in the form "ip:port"
   */
  String getAddress() {
    final InetSocketAddress address = hazelcast.getCluster().getLocalMember()
        .getSocketAddress();
    return address.getHostString() + ":" + address.getPort();
  }

  /**
   * Shuts down the global runtime.
   */
  void shutdown() {
    kill.run();
    hazelcast.shutdown();
  }

  /**
   * Returns the number of places in the global runtime.
   *
   * @return the number of Hazelcast instances that have joined the Hazelcast
   *         cluster
   */
  int places() {
    return places;
  }

  /**
   * Returns the current place ID.
   *
   * @return the ID of this Hazelcast instance in the Hazelcast cluster.
   */
  int here() {
    return here;
  }

  /**
   * Executes a function at the given place.
   *
   * @param place
   *          the requested place of execution
   * @param f
   *          the function to execute
   */
  void send(int place, SerializableRunnable f) {
    if (place == here) {
      f.run();
    } else {
      executor.executeOnMember(f, members.get(place));
      // hazelcast.getTopic("APGAS" + place).publish(f);
    }
  }

  @Override
  public void memberAdded(MembershipEvent membershipEvent) {
  }

  @Override
  public void memberRemoved(MembershipEvent membershipEvent) {
    members.removeItemListener(regItemListener);
    hazelcast.getCluster().removeMembershipListener(regMembershipListener);
    // topic.removeMessageListener(regTopic);
    shutdown();
  }

  @Override
  public void memberAttributeChanged(MemberAttributeEvent memberAttributeEvent) {
  }

  @Override
  public void itemAdded(ItemEvent<Member> item) {
    places = members.size();
  }

  @Override
  public void itemRemoved(ItemEvent<Member> item) {
  }

  @Override
  public void onMessage(Message<Runnable> message) {
    message.getMessageObject().run();
  }
}
