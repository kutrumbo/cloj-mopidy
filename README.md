# cloj-mopidy

A Clojure library for controlling a [Modipy music server](https://www.mopidy.com/)

### Available RPC Methods

```
:core.playlists.get_playlists Get the available playlists.

:rtype: list of :class:`mopidy.models.Playlist`

.. versionchanged:: 1.0
    If you call the method with ``include_tracks=False``, the
    :attr:`~mopidy.models.Playlist.last_modified` field of the returned
    playlists is no longer set.

.. deprecated:: 1.0
    Use :meth:`as_list` and :meth:`get_items` instead.
```
```
:core.tracklist.previous_track Returns the track that will be played if calling
:meth:`mopidy.core.PlaybackController.previous()`.

For normal playback this is the previous track in the tracklist. If
random and/or consume is enabled it should return the current track
instead.

:param tl_track: the reference track
:type tl_track: :class:`mopidy.models.TlTrack` or :class:`None`
:rtype: :class:`mopidy.models.TlTrack` or :class:`None`
```
```
:core.tracklist.set_random Set random mode.

:class:`True`
    Tracks are selected at random from the tracklist.
:class:`False`
    Tracks are played in the order of the tracklist.
```
```
:core.mixer.get_volume Get the volume.

Integer in range [0..100] or :class:`None` if unknown.

The volume scale is linear.
```
```
:core.tracklist.get_random Get random mode.

:class:`True`
    Tracks are selected at random from the tracklist.
:class:`False`
    Tracks are played in the order of the tracklist.
```
```
:core.history.get_history Get the track history.

The timestamps are milliseconds since epoch.

:returns: the track history
:rtype: list of (timestamp, :class:`mopidy.models.Ref`) tuples
```
```
:core.tracklist.get_repeat Get repeat mode.

:class:`True`
    The tracklist is played repeatedly.
:class:`False`
    The tracklist is played once.
```
```
:core.playlists.lookup Lookup playlist with given URI in both the set of playlists and in any
other playlist sources. Returns :class:`None` if not found.

:param uri: playlist URI
:type uri: string
:rtype: :class:`mopidy.models.Playlist` or :class:`None`
```
```
:core.history.get_length Get the number of tracks in the history.

:returns: the history length
:rtype: int
```
```
:core.library.lookup Lookup the given URI.

If the URI expands to multiple tracks, the returned list will contain
them all.

:param uri: track URI
:type uri: string or :class:`None`
:param uris: track URIs
:type uris: list of string or :class:`None`
:rtype: list of :class:`mopidy.models.Track` if uri was set or
    a {uri: list of :class:`mopidy.models.Track`} if uris was set.

.. versionadded:: 1.0
    The ``uris`` argument.

.. deprecated:: 1.0
    The ``uri`` argument. Use ``uris`` instead.
```
```
:core.library.search Search the library for tracks where ``field`` contains ``values``.

.. deprecated:: 1.0
    Previously, if the query was empty, and the backend could support
    it, all available tracks were returned. This has not changed, but
    it is strongly discouraged. No new code should rely on this
    behavior.

If ``uris`` is given, the search is limited to results from within the
URI roots. For example passing ``uris=['file:']`` will limit the search
to the local backend.

Examples::

    # Returns results matching 'a' in any backend
    search({'any': ['a']})
    search(any=['a'])

    # Returns results matching artist 'xyz' in any backend
    search({'artist': ['xyz']})
    search(artist=['xyz'])

    # Returns results matching 'a' and 'b' and artist 'xyz' in any
    # backend
    search({'any': ['a', 'b'], 'artist': ['xyz']})
    search(any=['a', 'b'], artist=['xyz'])

    # Returns results matching 'a' if within the given URI roots
    # "file:///media/music" and "spotify:"
    search({'any': ['a']}, uris=['file:///media/music', 'spotify:'])
    search(any=['a'], uris=['file:///media/music', 'spotify:'])

:param query: one or more queries to search for
:type query: dict
:param uris: zero or more URI roots to limit the search to
:type uris: list of strings or :class:`None`
:rtype: list of :class:`mopidy.models.SearchResult`

.. versionadded:: 1.0
    The ``exact`` keyword argument, which replaces :meth:`find_exact`.
```
```
:core.playback.get_state Get The playback state.
```
```
:core.playback.get_current_tl_track Get the currently playing or selected track.

Returns a :class:`mopidy.models.TlTrack` or :class:`None`.
```
```
:core.playlists.refresh Refresh the playlists in :attr:`playlists`.

If ``uri_scheme`` is :class:`None`, all backends are asked to refresh.
If ``uri_scheme`` is an URI scheme handled by a backend, only that
backend is asked to refresh. If ``uri_scheme`` doesn't match any
current backend, nothing happens.

:param uri_scheme: limit to the backend matching the URI scheme
:type uri_scheme: string
```
```
:core.playback.get_stream_title Get the current stream title or :class:`None`.
```
```
:core.playlists.as_list Get a list of the currently available playlists.

Returns a list of :class:`~mopidy.models.Ref` objects referring to the
playlists. In other words, no information about the playlists' content
is given.

:rtype: list of :class:`mopidy.models.Ref`

.. versionadded:: 1.0
```
```
:core.tracklist.get_tl_tracks Get tracklist as list of :class:`mopidy.models.TlTrack`.
```
```
:core.tracklist.slice Returns a slice of the tracklist, limited by the given start and end
positions.

:param start: position of first track to include in slice
:type start: int
:param end: position after last track to include in slice
:type end: int
:rtype: :class:`mopidy.models.TlTrack`
```
```
:core.get_version Get version of the Mopidy core API
```
```
:core.playback.get_mute .. deprecated:: 1.0
    Use :meth:`core.mixer.get_mute()
    <mopidy.core.MixerController.get_mute>` instead.
```
```
:core.playback.next Change to the next track.

The current playback state will be kept. If it was playing, playing
will continue. If it was paused, it will still be paused, etc.
```
```
:core.playback.get_current_track Get the currently playing or selected track.

Extracted from :meth:`get_current_tl_track` for convenience.

Returns a :class:`mopidy.models.Track` or :class:`None`.
```
```
:core.tracklist.set_repeat Set repeat mode.

To repeat a single track, set both ``repeat`` and ``single``.

:class:`True`
    The tracklist is played repeatedly.
:class:`False`
    The tracklist is played once.
```
```
:core.library.get_images Lookup the images for the given URIs

Backends can use this to return image URIs for any URI they know about
be it tracks, albums, playlists... The lookup result is a dictionary
mapping the provided URIs to lists of images.

Unknown URIs or URIs the corresponding backend couldn't find anything
for will simply return an empty list for that URI.

:param list uris: list of URIs to find images for
:rtype: {uri: tuple of :class:`mopidy.models.Image`}

.. versionadded:: 1.0
```
```
:core.playback.seek Seeks to time position given in milliseconds.

:param time_position: time position in milliseconds
:type time_position: int
:rtype: :class:`True` if successful, else :class:`False`
```
```
:core.tracklist.get_tracks Get tracklist as list of :class:`mopidy.models.Track`.
```
```
:core.playback.stop Stop playing.
```
```
:core.playback.play Play the given track, or if the given track is :class:`None`, play the
currently active track.

:param tl_track: track to play
:type tl_track: :class:`mopidy.models.TlTrack` or :class:`None`
```
```
:core.tracklist.set_single Set single mode.

:class:`True`
    Playback is stopped after current song, unless in ``repeat`` mode.
:class:`False`
    Playback continues after current song.
```
```
:core.mixer.get_mute Get mute state.

:class:`True` if muted, :class:`False` unmuted, :class:`None` if
unknown.
```
```
:core.playlists.filter Filter playlists by the given criterias.

Examples::

    # Returns track with name 'a'
    filter({'name': 'a'})
    filter(name='a')

    # Returns track with URI 'xyz'
    filter({'uri': 'xyz'})
    filter(uri='xyz')

    # Returns track with name 'a' and URI 'xyz'
    filter({'name': 'a', 'uri': 'xyz'})
    filter(name='a', uri='xyz')

:param criteria: one or more criteria to match by
:type criteria: dict
:rtype: list of :class:`mopidy.models.Playlist`

.. deprecated:: 1.0
    Use :meth:`as_list` and filter yourself.
```
```
:core.tracklist.next_track The track that will be played if calling
:meth:`mopidy.core.PlaybackController.next()`.

For normal playback this is the next track in the tracklist. If repeat
is enabled the next track can loop around the tracklist. When random is
enabled this should be a random track, all tracks should be played once
before the tracklist repeats.

:param tl_track: the reference track
:type tl_track: :class:`mopidy.models.TlTrack` or :class:`None`
:rtype: :class:`mopidy.models.TlTrack` or :class:`None`
```
```
:core.playback.get_time_position Get time position in milliseconds.
```
```
:core.playlists.get_items Get the items in a playlist specified by ``uri``.

Returns a list of :class:`~mopidy.models.Ref` objects referring to the
playlist's items.

If a playlist with the given ``uri`` doesn't exist, it returns
:class:`None`.

:rtype: list of :class:`mopidy.models.Ref`, or :class:`None`

.. versionadded:: 1.0
```
```
:core.playback.resume If paused, resume playing the current track.
```
```
:core.playback.set_mute .. deprecated:: 1.0
    Use :meth:`core.mixer.set_mute()
    <mopidy.core.MixerController.set_mute>` instead.
```
```
:core.get_uri_schemes Get list of URI schemes we can handle
```
```
:core.playback.pause Pause playback.
```
```
:core.playlists.create Create a new playlist.

If ``uri_scheme`` matches an URI scheme handled by a current backend,
that backend is asked to create the playlist. If ``uri_scheme`` is
:class:`None` or doesn't match a current backend, the first backend is
asked to create the playlist.

All new playlists must be created by calling this method, and **not**
by creating new instances of :class:`mopidy.models.Playlist`.

:param name: name of the new playlist
:type name: string
:param uri_scheme: use the backend matching the URI scheme
:type uri_scheme: string
:rtype: :class:`mopidy.models.Playlist`
```
```
:core.tracklist.shuffle Shuffles the entire tracklist. If ``start`` and ``end`` is given only
shuffles the slice ``[start:end]``.

Triggers the :meth:`mopidy.core.CoreListener.tracklist_changed` event.

:param start: position of first track to shuffle
:type start: int or :class:`None`
:param end: position after last track to shuffle
:type end: int or :class:`None`
```
```
:core.tracklist.get_version Get the tracklist version.

Integer which is increased every time the tracklist is changed. Is not
reset before Mopidy is restarted.
```
```
:core.tracklist.get_single Get single mode.

:class:`True`
    Playback is stopped after current song, unless in ``repeat`` mode.
:class:`False`
    Playback continues after current song.
```
```
:core.playback.get_volume .. deprecated:: 1.0
    Use :meth:`core.mixer.get_volume()
    <mopidy.core.MixerController.get_volume>` instead.
```
```
:core.tracklist.set_consume Set consume mode.

:class:`True`
    Tracks are removed from the tracklist when they have been played.
:class:`False`
    Tracks are not removed from the tracklist.
```
```
:core.playback.set_volume .. deprecated:: 1.0
    Use :meth:`core.mixer.set_volume()
    <mopidy.core.MixerController.set_volume>` instead.
```
```
:core.tracklist.get_length Get length of the tracklist.
```
```
:core.library.get_distinct List distinct values for a given field from the library.

This has mainly been added to support the list commands the MPD
protocol supports in a more sane fashion. Other frontends are not
recommended to use this method.

:param string field: One of ``track``, ``artist``, ``albumartist``,
    ``album``, ``composer``, ``performer``, ``date``or ``genre``.
:param dict query: Query to use for limiting results, see
    :meth:`search` for details about the query format.
:rtype: set of values corresponding to the requested field type.

.. versionadded:: 1.0
```
```
:core.library.find_exact Search the library for tracks where ``field`` is ``values``.

.. deprecated:: 1.0
    Use :meth:`search` with ``exact`` set.
```
```
:core.playback.previous Change to the previous track.

The current playback state will be kept. If it was playing, playing
will continue. If it was paused, it will still be paused, etc.
```
```
:core.library.refresh Refresh library. Limit to URI and below if an URI is given.

:param uri: directory or track URI
:type uri: string
```
```
:core.library.browse Browse directories and tracks at the given ``uri``.

``uri`` is a string which represents some directory belonging to a
backend. To get the intial root directories for backends pass None as
the URI.

Returns a list of :class:`mopidy.models.Ref` objects for the
directories and tracks at the given ``uri``.

The :class:`~mopidy.models.Ref` objects representing tracks keep the
track's original URI. A matching pair of objects can look like this::

    Track(uri='dummy:/foo.mp3', name='foo', artists=..., album=...)
    Ref.track(uri='dummy:/foo.mp3', name='foo')

The :class:`~mopidy.models.Ref` objects representing directories have
backend specific URIs. These are opaque values, so no one but the
backend that created them should try and derive any meaning from them.
The only valid exception to this is checking the scheme, as it is used
to route browse requests to the correct backend.

For example, the dummy library's ``/bar`` directory could be returned
like this::

    Ref.directory(uri='dummy:directory:/bar', name='bar')

:param string uri: URI to browse
:rtype: list of :class:`mopidy.models.Ref`

.. versionadded:: 0.18
```
```
:core.playback.set_state Set the playback state.

Must be :attr:`PLAYING`, :attr:`PAUSED`, or :attr:`STOPPED`.

Possible states and transitions:

.. digraph:: state_transitions

    "STOPPED" -> "PLAYING" [ label="play" ]
    "STOPPED" -> "PAUSED" [ label="pause" ]
    "PLAYING" -> "STOPPED" [ label="stop" ]
    "PLAYING" -> "PAUSED" [ label="pause" ]
    "PLAYING" -> "PLAYING" [ label="play" ]
    "PAUSED" -> "PLAYING" [ label="resume" ]
    "PAUSED" -> "STOPPED" [ label="stop" ]
```
```
:core.tracklist.move Move the tracks in the slice ``[start:end]`` to ``to_position``.

Triggers the :meth:`mopidy.core.CoreListener.tracklist_changed` event.

:param start: position of first track to move
:type start: int
:param end: position after last track to move
:type end: int
:param to_position: new position for the tracks
:type to_position: int
```
```
:core.mixer.set_volume Set the volume.

The volume is defined as an integer in range [0..100].

The volume scale is linear.

Returns :class:`True` if call is successful, otherwise :class:`False`.
```
```
:core.playlists.save Save the playlist.

For a playlist to be saveable, it must have the ``uri`` attribute set.
You must not set the ``uri`` atribute yourself, but use playlist
objects returned by :meth:`create` or retrieved from :attr:`playlists`,
which will always give you saveable playlists.

The method returns the saved playlist. The return playlist may differ
from the saved playlist. E.g. if the playlist name was changed, the
returned playlist may have a different URI. The caller of this method
must throw away the playlist sent to this method, and use the
returned playlist instead.

If the playlist's URI isn't set or doesn't match the URI scheme of a
current backend, nothing is done and :class:`None` is returned.

:param playlist: the playlist
:type playlist: :class:`mopidy.models.Playlist`
:rtype: :class:`mopidy.models.Playlist` or :class:`None`
```
```
:core.tracklist.index The position of the given track in the tracklist.

:param tl_track: the track to find the index of
:type tl_track: :class:`mopidy.models.TlTrack`
:rtype: :class:`int` or :class:`None`
```
```
:core.tracklist.remove Remove the matching tracks from the tracklist.

Uses :meth:`filter()` to lookup the tracks to remove.

Triggers the :meth:`mopidy.core.CoreListener.tracklist_changed` event.

:param criteria: on or more criteria to match by
:type criteria: dict
:rtype: list of :class:`mopidy.models.TlTrack` that was removed
```
```
:core.mixer.set_mute Set mute state.

:class:`True` to mute, :class:`False` to unmute.

Returns :class:`True` if call is successful, otherwise :class:`False`.
```
```
:core.tracklist.eot_track The track that will be played after the given track.

Not necessarily the same track as :meth:`next_track`.

:param tl_track: the reference track
:type tl_track: :class:`mopidy.models.TlTrack` or :class:`None`
:rtype: :class:`mopidy.models.TlTrack` or :class:`None`
```
```
:core.tracklist.get_consume Get consume mode.

:class:`True`
    Tracks are removed from the tracklist when they have been played.
:class:`False`
    Tracks are not removed from the tracklist.
```
```
:core.playlists.delete Delete playlist identified by the URI.

If the URI doesn't match the URI schemes handled by the current
backends, nothing happens.

:param uri: URI of the playlist to delete
:type uri: string
```
```
:core.tracklist.add Add the track or list of tracks to the tracklist.

If ``uri`` is given instead of ``tracks``, the URI is looked up in the
library and the resulting tracks are added to the tracklist.

If ``uris`` is given instead of ``tracks``, the URIs are looked up in
the library and the resulting tracks are added to the tracklist.

If ``at_position`` is given, the tracks placed at the given position in
the tracklist. If ``at_position`` is not given, the tracks are appended
to the end of the tracklist.

Triggers the :meth:`mopidy.core.CoreListener.tracklist_changed` event.

:param tracks: tracks to add
:type tracks: list of :class:`mopidy.models.Track`
:param at_position: position in tracklist to add track
:type at_position: int or :class:`None`
:param uri: URI for tracks to add
:type uri: string
:rtype: list of :class:`mopidy.models.TlTrack`

.. versionadded:: 1.0
    The ``uris`` argument.

.. deprecated:: 1.0
    The ``tracks`` and ``uri`` arguments. Use ``uris``.
```
```
:core.tracklist.clear Clear the tracklist.

Triggers the :meth:`mopidy.core.CoreListener.tracklist_changed` event.
```
```
:core.tracklist.filter Filter the tracklist by the given criterias.

A criteria consists of a model field to check and a list of values to
compare it against. If the model field matches one of the values, it
may be returned.

Only tracks that matches all the given criterias are returned.

Examples::

    # Returns tracks with TLIDs 1, 2, 3, or 4 (tracklist ID)
    filter({'tlid': [1, 2, 3, 4]})
    filter(tlid=[1, 2, 3, 4])

    # Returns track with IDs 1, 5, or 7
    filter({'id': [1, 5, 7]})
    filter(id=[1, 5, 7])

    # Returns track with URIs 'xyz' or 'abc'
    filter({'uri': ['xyz', 'abc']})
    filter(uri=['xyz', 'abc'])

    # Returns tracks with ID 1 and URI 'xyz'
    filter({'id': [1], 'uri': ['xyz']})
    filter(id=[1], uri=['xyz'])

    # Returns track with a matching ID (1, 3 or 6) and a matching URI
    # ('xyz' or 'abc')
    filter({'id': [1, 3, 6], 'uri': ['xyz', 'abc']})
    filter(id=[1, 3, 6], uri=['xyz', 'abc'])

:param criteria: on or more criteria to match by
:type criteria: dict, of (string, list) pairs
:rtype: list of :class:`mopidy.models.TlTrack`
```